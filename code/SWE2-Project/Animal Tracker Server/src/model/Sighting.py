from typing import Optional


class Sighting:
    """
    Represents a documented sighting of an animal.
    """

    def __init__(self, animal_tag, username, location, latitude, longitude, time, notes):
        """
        Initializes an instance of a Sighting.
        :param animal_tag: Tag for an animal.
        :param location: Location of sighting.
        :param latitude: Latitude of sighting.
        :param longitude: Longitude of sighting.
        :param time: Time of sighting.
        :param notes: Notes of sighting.
        """
        if animal_tag is None:
            raise ValueError("animal_tag must be provided.")

        if username is None:
            raise ValueError("username must be provided.")

        if self._is_invalid_location(location):
            raise ValueError("Location must be provided.")

        if self._is_invalid_latitude(latitude):
            raise ValueError("Latitude must be a valid coordinate between -90 and 90.")

        if self._is_invalid_longitude(longitude):
            raise ValueError("Longitude must be a valid coordinate between -180 and 180.")

        if self._is_invalid_time(time):
            raise ValueError("Sighting time must be provided.")

        if self._is_invalid_notes(notes):
            raise ValueError("Notes must contain valid text.")

        self._animal_tag = animal_tag
        self._username = username
        self._location = location
        self._latitude = latitude
        self._longitude = longitude
        self._time = time
        self._notes = notes

    @staticmethod
    def _is_invalid_location(location):
        """
        Checks if the location is invalid.
        :param location: Location of sighting.
        :return: True or False on whether the location is invalid.
        """
        return location is None or location.strip() == ""

    @staticmethod
    def _is_invalid_notes(notes: Optional[str]) -> bool:
        """
        Checks if the notes are invalid.
        :param notes: Notes of animal sighting.
        :return: True or False on whether the notes are invalid.
        """
        return notes is None or notes.strip() == ""

    @staticmethod
    def _is_invalid_time(time):
        """
        Checks if the time is invalid.
        :param time: Time of animal sighting.
        :return: True or False on whether the time is invalid.
        """
        return time is None

    @staticmethod
    def _is_invalid_longitude(longitude):
        """
        Checks if the longitude is invalid.
        :param longitude: Longitude of animal sighting.
        :return: True or False on whether the longitude is invalid.
        """
        return longitude < -180.0 or longitude > 180.0

    @staticmethod
    def _is_invalid_latitude(latitude):
        """
        Checks if the latitude is invalid.
        :param latitude: Latitude of animal sighting.
        :return: True or False on whether the latitude is invalid.
        """
        return latitude < -90.0 or latitude > 90.0

    def get_username(self):
        return self._username

    def get_animal_tag(self):
        """
        Gets the animal tag.
        :return: The animal tag.
        """
        return self._animal_tag

    def get_location(self):
        """
        Gets the location.
        :return: The location of sighting.
        """
        return self._location

    def get_latitude(self):
        """
        Gets the latitude.
        :return: The latitude of sighting.
        """
        return self._latitude

    def get_longitude(self):
        """
        Gets the longitude.
        :return: The longitude of sighting.
        """
        return self._longitude

    def get_time(self):
        """
        Gets the time of sighting.
        :return: The time of sighting.
        """
        return self._time

    def get_notes(self):
        """
        Gets the notes of sighting.
        :return: The notes of sighting.
        """
        return self._notes
