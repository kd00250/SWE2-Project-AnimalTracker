from model.data.UserStorage import UserStorage

"""
The project storage which updates and stores projects.
"""
class ProjectStorage:
    """
    Instantiates a new project storage
    """
    def __init__(self):
        self._projects = []
        self._id_to_project = {}

    """
    Adds a new project to the storage
    """
    def add_project(self, project):
        if project not in self._projects:
            project.id = len(self._projects)
            self._projects.append(project)
            self._id_to_project[project.id] = project
    """
    Removes a project from the storage
    """
    def remove_project(self, project):
        project_id = project.id
        if id not in self._id_to_project:
            return
        self._projects.remove(project)
        del self._id_to_project[project_id]
    """
    Gets a project from the storage
    """
    def get_project(self, project_id):
        if project_id not in self._id_to_project:
            return None
        return self._id_to_project[project_id]
    """
    Updates a projects name inside the storage.
    """
    def update_project_name(self, project_id, name):
        if project_id not in self._id_to_project:
            return False
        self._id_to_project[project_id].name = name
        return True

    def update_add_animal(self, project_id, animal):
        if project_id not in self._id_to_project:
            return False
        self._id_to_project[project_id].animal.add(animal)
        return True

    def update_add_user(self, project_id, user):
        if project_id not in self._id_to_project:
            return False
        self._id_to_project[project_id].add_user(user)
        return True

    def update_remove_user(self, project_id, user):
        if project_id not in self._id_to_project:
            return False
        self._id_to_project[project_id].remove_user(user)
        return True
