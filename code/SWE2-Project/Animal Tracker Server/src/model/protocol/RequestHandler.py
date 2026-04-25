import json
from datetime import datetime

from model.Animal import Animal
from model.AnimalClass import AnimalClass
from model.Project import Project
from model.Role import Role
from model.Sighting import Sighting
from model.User import User
from model.authentication.Authenticator import Authenticator
from model.data.ServerStorage import ServerStorage
from model.protocol.ResponseBuilder import ResponseBuilder


class RequestHandler:
    """
    Processes client requests and delegates actions to the appropriate
    services such as authentication, storage, and response builder.
    """
    storage = ServerStorage()

    @staticmethod
    def handle_request(message):
        """
        Parses an incoming request and routes it to the appropriate handler.
        :param message: Raw JSON string containing request data
        :return: Response object generated based on the request
        """
        request = json.loads(message)
        print(f"Server - Received request: {request}")

        handlers = {
            "login": RequestHandler._handle_login,
            "user_role_request": RequestHandler._handle_get_user_role_request,
            "add_user_request": RequestHandler._handle_add_user_request,
            "get_project_list_request": RequestHandler._handle_get_project_list_request,
            "get_project_request": RequestHandler._handle_get_project_request,
            "delete_project_request": RequestHandler._handle_delete_project_request,
            "get_scientist_request": RequestHandler._handle_get_scientist_request,
            "create_project_request": RequestHandler._handle_create_project_request,
            "add_animal_request": RequestHandler._handle_add_animal_request,
            "add_sighting_request": RequestHandler._handle_add_sighting_request,
            "get_sightings_request": RequestHandler._handle_get_sighting_request,
        }

        action = request.get("action")
        handler = handlers.get(action)

        if handler is None:
            return None

        return handler(request)

    @staticmethod
    def _handle_login(request):
        print("Handle login")
        has_token = Authenticator.check_login(request)
        response = ResponseBuilder.build_login_response(has_token)
        return response

    @staticmethod
    def _handle_get_user_role_request(request):
        print("Handle get user role request")
        token = request.get("token")
        user = RequestHandler.storage.get_user(token)
        role = user.get_role().name
        response = ResponseBuilder.build_get_role_response(role)
        return response

    @staticmethod
    def _handle_add_user_request(request):
        print("Handle add user request")
        username = request.get("username")
        if not RequestHandler.storage.contains_username(username):
            password = request.get("password")
            role = Role[request.get("role")]
            user = User(username, password, role)
            RequestHandler.storage.add_user(user)
            return ResponseBuilder.build_add_user_response(user)
        return ResponseBuilder.build_user_exists_response()

    @staticmethod
    def _handle_get_project_list_request(request):
        print("Handling get project list")

        token = request.get("token")
        user = RequestHandler.storage.get_user(token)
        role = user.get_role()

        if role == Role.SCIENTIST:
            projects = RequestHandler.storage.retrieve_projects_from_user(user)
            return ResponseBuilder.build_retrieved_projects_response(projects)
        return ResponseBuilder.build_user_does_not_have_permission_response()

    @staticmethod
    def _handle_get_project_request(request):
        print("Handle get project request")
        project_id = request.get("project id")
        print(project_id)

        project_name = request.get("project name")
        project = RequestHandler.storage.get_project(project_id)

        if project is None:
            return ResponseBuilder.build_could_not_find_project()
        print(project)
        print(project.get_name())

        if int(project_id) == project.get_id() and project_name == project.get_name():
            return ResponseBuilder.build_get_project_response(project)
        else:
            return ResponseBuilder.build_could_not_find_project()

    @staticmethod
    def _handle_delete_project_request(request):
        print("Handling delete request")
        project_id = request.get("project id")
        token = request.get("token")

        user = RequestHandler.storage.get_user(token)
        has_removed_project = False
        if user is None:
            return ResponseBuilder.build_removed_project(has_removed_project)

        for project in RequestHandler.storage.retrieve_projects_from_user(user):
            if project.get_id() == int(project_id):
                has_removed_project = RequestHandler.storage.remove_project(project)
                break

        return ResponseBuilder.build_removed_project(has_removed_project)

    @staticmethod
    def _handle_get_scientist_request(request):
        print("Handling get scientist request")
        token = request.get("token")
        users = RequestHandler.storage.get_all_users()
        if token is None:
            return ResponseBuilder.build_token_does_not_exist()
        else:
            project_creator = RequestHandler.storage.get_user(token)
            return ResponseBuilder.build_get_scientist_response(users, project_creator)

    @staticmethod
    def _handle_create_project_request(request):
        print("Handling Create Project Request")
        token = request.get("token")
        user = {RequestHandler.storage.get_user(token)}
        project_name = request.get("project name")
        project_id = len(RequestHandler.storage.retrieve_projects_in_server()) + 1
        users = request.get("users", [])

        project = Project(project_name, user, set(), project_id)

        for current_user in users:
            found_user = RequestHandler.storage.get_user_with_username(current_user)

            if found_user is not None:
                project.add_user(found_user)

        has_created_project = RequestHandler.storage.add_project(project)
        return ResponseBuilder.build_create_project_response(has_created_project)

    @staticmethod
    def _handle_add_animal_request(request):
        print("Handling add animal request")
        project_id = int(request.get("project id"))
        project = RequestHandler.storage.get_project(project_id)

        if project is None:
            print("NONE")
            return ResponseBuilder.build_could_not_find_project()
        else:
            animal_class = request.get("Class")
            animal_class = AnimalClass[animal_class]
            height = float(request.get("Height"))
            weight = float(request.get("Weight"))
            length = float(request.get("Length"))
            tag_id = int(request.get("TagID"))
            description = request.get("Description")
            animal = Animal(animal_class, height, weight, length, tag_id, description)
            animal = RequestHandler.storage.update_add_animal(project_id, animal)

            return ResponseBuilder.build_add_animal_response(animal)

    @staticmethod
    def _handle_add_sighting_request(request):
        print("Handling add sighting request")
        token = request.get("token")
        user = RequestHandler.storage.get_user(token)

        if user is None:
            return ResponseBuilder.build_user_is_not_in_system()
        username = user.get_username()

        if not RequestHandler.storage.contains_username(username):
            return ResponseBuilder.build_user_is_not_in_system()

        animal_tag = int(request.get("animal"))
        if not RequestHandler.storage.is_animal_tag_in_server(animal_tag):
            return ResponseBuilder.build_tag_does_not_exist()

        location = request.get("location")
        latitude = float(request.get("latitude"))
        longitude = float(request.get("longitude"))
        time = request.get("time")

        try:
            time = datetime.fromisoformat(time) if time else None
        except ValueError:
            return ResponseBuilder.build_invalid_time()

        notes = request.get("notes")
        sighting = Sighting(animal_tag, username, location, latitude, longitude, time, notes)
        RequestHandler.storage.add_sighting(sighting)
        return ResponseBuilder.build_add_sighting_response(sighting)

    @staticmethod
    def _handle_get_sighting_request(request):
        print("Handling get sighting request")
        token = request.get("token")
        if token is None:
            return ResponseBuilder.build_token_does_not_exist()

        animal_tag = request.get("tagID")
        if animal_tag is None:
            return ResponseBuilder.build_tag_does_not_exist()

        sightings = RequestHandler.storage.retrieve_sightings_by_animal_id(animal_tag)
        return ResponseBuilder.build_get_sighting_response(sightings)
