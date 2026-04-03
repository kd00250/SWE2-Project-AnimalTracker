
class Project:
    def __init__(self, name, users, animals, project_id):
        if name is None:
            raise Exception("Project name cannot be None")
        if users is None:
            raise Exception("Project users cannot be None")
        if project_id < 0:
            raise Exception("Project project_id cannot be negative")
        if name.strip() == "":
            raise Exception("Project name cannot be an empty string")
        self._name = name
        self._users = users
        self._animals = animals
        self._id = project_id

    def get_name(self):
        return self._name
    def get_users(self):
        return self._users
    def get_animals(self):
        return self._animals
    def get_id(self):
        return self._id
    def set_name(self, name):
        if name is None:
            raise Exception("Project name cannot be None")
        if name.strip() == "":
            raise Exception("Project name cannot be an empty string")
        self._name = name
    def set_id(self, id):
        if id is None:
            raise Exception("Project id cannot be None")
        if id < 0:
            raise Exception("Project id cannot be negative")
        self._id = id

    def add_user(self, user):
        if user is None:
            raise Exception("User cannot be None")
        if self.contains_user(user):
            raise Exception("User already exists")
        self._users.append(user)

    def contains_user(self, user):
        return user in self._users

    def add_animal(self, animal):
        if animal is None:
            raise Exception("Animal cannot be None")
        if self.contains_animal(animal):
            raise Exception("Animal already exists")
        self._animals.append(animal)
    def contains_animal(self, animal):
        return animal in self._animals

    def remove_user(self, user):
        if user is None:
            return
        self._users.remove(user)