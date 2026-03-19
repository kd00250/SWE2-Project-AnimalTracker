from model.TagStatus import TagStatus


class Animal:
    def __init__(self, animal_class, height, weight, length, tag_id, description):
        if description is None:
            raise Exception("Description is required")
        if animal_class is None:
            raise Exception("AnimalClass is required")

        if height <= 0:
            raise Exception("Height must be positive")
        if weight <= 0:
            raise Exception("Weight must be positive")
        if length <= 0:
            raise Exception("Length must be positive")
        if tag_id <= 0:
            raise Exception("TagId must be positive")
        self._animal_class = animal_class
        self._height = height
        self._weight = weight
        self._length = length
        self._tagId = tag_id
        self._description = description
        self._tag_status = TagStatus.ACTIVE

    def get_animal_class(self):
        return self._animal_class
    def get_height(self):
        return self._height
    def get_weight(self):
        return self._weight
    def get_length(self):
        return self._length
    def get_tag_id(self):
        return self._tagId
    def get_tag_status(self):
        return self._tag_status
    def get_description(self):
        return self._description

