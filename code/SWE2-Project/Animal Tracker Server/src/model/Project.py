
class Project:
    def __init__(self, name, users, animals, project_id):
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
        self._name = name
    def set_id(self, id):
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
            raise Exception("User cannot be None")
        self._users.remove(user)