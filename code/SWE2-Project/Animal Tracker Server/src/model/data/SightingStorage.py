class SightingStorage:
    """
    The sighting class that manages sightings for the server.
    """

    def __init__(self):
        """
        Initializes the SightingStorage class.
        """
        self._sightings = []

    def add_sighting(self, sighting):
        """
        Adds a sighting to the sighting list.
        :param sighting: Sighting object.
        :return: True if the sighting was added. Otherwise, False.
        """
        if sighting is None:
            raise Exception('Sighting cannot be None')

        if sighting not in self._sightings:
            self._sightings.append(sighting)

        return True

    def remove_sighting(self, sighting):
        """
        Removes a sighting from the sighting list.
        :param sighting: Sighting object.
        :return: True if sighting was removed. Otherwise, False.
        """
        if sighting not in self._sightings:
            return False

        self._sightings.remove(sighting)
        return True

    def retrieve_all_sightings(self):
        """
        Retrieves all sightings from the sighting list.
        :return: All sightings from the sighting list.
        """
        return self._sightings

    def retrieve_sightings_by_animal_id(self, animal_tag):
        """
        Retrieves all sightings from the sighting list by animal tag.
        :param animal_tag: Id of an animal.
        :return: All sightings from the sighting list by animal tag.
        """
        for sighting in self._sightings:
            if sighting.get_animal_tag() == animal_tag:
                yield sighting

    def _reset(self):
        self._sightings = []
