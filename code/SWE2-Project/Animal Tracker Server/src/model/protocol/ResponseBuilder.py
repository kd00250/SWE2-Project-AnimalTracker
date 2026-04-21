from model.Role import Role


class ResponseBuilder:
    """
    Response builder class.
    Builds responses based on the type of request
    """

    @staticmethod
    def build_login_response(has_token):
        """
        Builds the response for a login request.
        :param has_token: Authentication token if login succeeds
        :return: JSON - Response object generated based on the request
        """
        if has_token:
            print("Server - Received login, sending request to client...")
            response = {
                "token": has_token
            }
        else:
            print("Server - Login Failed")
            response = {
                "status": "error",
            }

        return response

    @staticmethod
    def build_get_role_response(has_role):
        """
        Builds the response for get role request.
        :param has_role: Role of the user
        :return: JSON - Response containing role or error status
        """
        if has_role:
            print("Server - Received get role, sending request to client...")
            print(has_role)
            print(type(has_role))
            response = {
                "role": has_role
            }
            print(response)
        else:
            print("Server - Get role Failed")
            response = {
                "status": "error",
            }
        return response

    @staticmethod
    def build_add_user_response(user):
        """
        Builds the response for add user request.
        :param user: User object that was added
        :return: JSON - Response object generated based on the request
        """
        if user is not None:
            print("Server - Received add user, sending request to client...")
            response = {
                "status": "success",
            }
        else:
            print("Server - Add user Failed")
            response = {
                "status": "error",
            }
        return response

    @staticmethod
    def build_user_exists_response():
        """
        Builds the response for user exists request.
        :return: JSON - Error response
        """
        print("Server - User exists Failed")
        response = {
            "status": "error",
        }
        return response

    @staticmethod
    def build_retrieved_projects_response(projects):
        """
        Builds the response for retrieved projects request.
        :param projects: Collection of project objects
        :return: JSON - Response object generated based on the request
        """
        print("Server - Received retrieved projects, sending request to client...")

        if projects is None:
            response = {
                "status": "error",
                "projects": []
            }
            return response

        project_list = []
        for project in projects:
            print(project.get_id())
            project_list.append({
                "name": project.get_name(),
                "id": project.get_id(),
            })
        response = {
            "projects": project_list
        }
        return response

    @staticmethod
    def build_user_does_not_have_permission_response():
        """
        Builds the response for user does not have a permission request.
        :return: JSON - Error response
        """
        print("Server - Has no Permission")
        response = {
            "status": "error",
        }
        return response

    @staticmethod
    def build_could_not_find_project():
        """
        Builds the response for could not find project request.
        :return: JSON - Error response
        """
        print("Server - Could not find project")
        response = {
            "status": "error",
        }
        return response

    @staticmethod
    def build_get_project_response(project):
        """
        Builds the response for get project request.
        :param project: Project object containing animal data
        :return: JSON - Response containing an animal list
        """
        animal_list = []
        animals = project.get_animals()
        if animals is None:
            animal_list.append({
                "Class": "",
                "Height": "",
                "Weight": "",
                "Length": "",
                "TagID": "",
                "Description": "",
            })
        else:
            for animal in animals:
                animal_list.append({
                    "Class": animal.get_animal_class().name,
                    "Height": animal.get_height(),
                    "Weight": animal.get_weight(),
                    "Length": animal.get_length(),
                    "TagID": animal.get_tag_id(),
                    "Description": animal.get_description(),
                })
        response = {
            "animals": animal_list
        }
        return response

    @staticmethod
    def build_removed_project(has_removed_project):
        """
        Builds the response for removed project request.
        :param has_removed_project: Boolean indicating whether the project
        :return: JSON - Success or Error
        """
        if has_removed_project:
            print("Server - Success, Received removed project, sending request to client...")
            response = {
                "status": "success",
            }
            return response
        else:
            print("Server - Removed project Failed")
            response = {
                "status": "error",
            }
            return response

    @staticmethod
    def build_token_does_not_exist():
        """
        Builds the response for token does not exist request.
        :return: JSON - Error response
        """
        response = {
            "status": "error-TokenDoesNotExist",
        }
        return response

    @staticmethod
    def build_get_scientist_response(users, project_creator):
        """
        Builds the response for get scientist request.
        :param users: Collection of user objects
        :param project_creator: User who created the project
        :return: JSON - Response containing a filtered user list
        """
        user_list = []

        for user in users:
            if user.get_role() == Role.SCIENTIST and user.get_username() != project_creator.get_username():
                user_list.append({
                    "username": user.get_username(),
                    "password": "******",
                })

        response = {
            "users": user_list
        }
        return response

    @staticmethod
    def build_create_project_response(created_project):
        """
        Builds the response for create project request.
        :param created_project: Integer project id or None
        :return: JSON - Success or Error
        """
        if created_project is None:
            response = {
                "status": "error",
            }
        else:
            response = {
                "status": "success",
            }
        return response

    @staticmethod
    def build_add_animal_request(animal):
        """
        Builds the response for add animal request.
        :param animal: Animal object that was added
        :return: JSON - Success or Error
        """
        if animal is None:
            response = {
                "status": "error",
            }
        else:
            response = {
                "status": "success",
            }
        return response

    @staticmethod
    def build_user_is_not_in_system():
        response = {
            "status": "error",
        }
        return response

    @staticmethod
    def build_add_sighting_request(sighting):
        if sighting is None:
            response = {
                "status": "error",
            }
        else:
            response = {
                "status": "success",
            }
        return response
