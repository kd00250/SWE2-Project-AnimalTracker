from model.TagStatus import TagStatus


class Animal:
    """
    The class that represents animals in the server
    """

    def __init__(self, animal_class, height, weight, length, tag_id, description):
        """
        Initializes the animal for the server.
        """
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
        """
        Gets the animal class.
        :return: The animal class.
        """
        return self._animal_class
    def get_height(self):
        """
        Gets the height for the animal.
        :return: The height for the animal.
        """
        return self._height
    def get_weight(self):
        """Gets the weight of the animal.
        :return: The weight of the animal."""
        return self._weight
    def get_length(self):
        """
        Gets the length of the animal.
        :return: The length of the animal.
        """
        return self._length
    def get_tag_id(self):
        """
        Gets the tagId of the animal.
        :return: The tagId of the animal.
        """
        return self._tagId
    def get_tag_status(self):
        """
        Gets the tag status for the animal
        :return: The tag status for the animal.
        """
        return self._tag_status
    def get_description(self):
        """
        Gets the description of the animal.
        :return: The description of the animal.
        """
        return self._description

