from model.data.UserStorage import UserStorage


class ProjectStorage:
    """
    The storage class that manages projects for the server.
    """

    def __init__(self):
        self._projects = []
        self._id_to_project = {}

    def add_project(self, project):
        if project is None:
            raise Exception('Project cannot be None')
        if project not in self._projects:
            self._projects.append(project)
            self._id_to_project[project.get_id()] = project
        return project.get_id()

    def remove_project(self, project):
        project_id = project.get_id()
        if project_id not in self._id_to_project:
            return False
        self._projects.remove(project)
        del self._id_to_project[project_id]
        return True

    def get_project(self, project_id):
        if project_id not in self._id_to_project:
            return None
        return self._id_to_project[project_id]

    def update_project_name(self, project_id, name):
        if project_id not in self._id_to_project:
            return False
        self._id_to_project[project_id].set_name(name)
        return True

    def update_add_animal(self, project_id, animal):
        if project_id not in self._id_to_project:
            return False
        self._id_to_project[project_id].add_animal(animal)
        return True

    def update_add_user(self, project_id, user):
        if project_id not in self._id_to_project:
            return False
        self._id_to_project[project_id].add_user(user)
        return True

    def retrieve_projects_from_user(self, user):
        for project in self._projects:
            if project.contains_user(user):
                print(project.get_id())
                yield project

    def retrieve_projects_in_server(self):
        return self._projects

    def is_animal_tag_in_server(self, animal_tag):
        for project in self._projects:
            for animal in project.get_animals():
                if animal.get_tag() == animal_tag:
                    return True
        return False

    def reset(self):
        self._projects = []
        self._id_to_project = {}