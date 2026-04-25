class ProjectStorage:
    """
    The storage class that manages projects for the server.
    """

    def __init__(self):
        """
        Initializes the project storage class.
        """
        self._projects = []
        self._id_to_project = {}

    def add_project(self, project):
        """
        Add a project to the storage.
        :param project: Project to add.
        :return: Project id if added successfully.
        """
        if project is None:
            raise Exception('Project cannot be None')
        if project not in self._projects:
            self._projects.append(project)
            self._id_to_project[project.get_id()] = project
        return project.get_id()

    def remove_project(self, project):
        """
        Remove a project from the storage.
        :param project: Project to remove.
        :return: True if the project was removed successfully.
        """
        project_id = project.get_id()
        if project_id not in self._id_to_project:
            return False
        self._projects.remove(project)
        del self._id_to_project[project_id]
        return True

    def get_project(self, project_id):
        """
        Get a project from the storage.
        :param project_id: Project id.
        :return: Project if found.
        """
        if project_id not in self._id_to_project:
            return None
        return self._id_to_project[project_id]

    def update_project_name(self, project_id, name):
        """
        Update a project name.
        :param project_id: Project id.
        :param name: Name of Project.
        :return: True if name was updated successfully.
        """
        if project_id not in self._id_to_project:
            return False
        self._id_to_project[project_id].set_name(name)
        return True

    def update_add_animal(self, project_id, animal):
        """
        Update an animal.
        :param project_id: ID of a project.
        :param animal: Animal to add.
        :return: True if animal was added successfully.
        """
        if project_id not in self._id_to_project:
            return False
        self._id_to_project[project_id].add_animal(animal)
        return True

    def update_add_user(self, project_id, user):
        """
        Update a user.
        :param project_id: ID of a project.
        :param user: User to add.
        :return: True if user was added successfully.
        """
        if project_id not in self._id_to_project:
            return False
        self._id_to_project[project_id].add_user(user)
        return True

    def retrieve_projects_from_user(self, user):
        """
        Retrieve projects from a user.
        :param user: User to retrieve projects from.
        :return: List of projects.
        """
        for project in self._projects:
            if project.contains_user(user):
                print(project.get_id())
                yield project

    def retrieve_projects_in_server(self):
        """
        Retrieve projects from a server.
        :return: List of projects.
        """
        return self._projects

    def is_animal_tag_in_server(self, animal_tag):
        """
        Check if an animal tag is in the server.
        :param animal_tag: Tag of an animal to check.
        :return: True if tag is found.
        """
        for project in self._projects:
            for animal in project.get_animals():
                if animal.get_tag_id() == animal_tag:
                    return True
        return False

    def _reset(self):
        self._projects = []
        self._id_to_project = {}
