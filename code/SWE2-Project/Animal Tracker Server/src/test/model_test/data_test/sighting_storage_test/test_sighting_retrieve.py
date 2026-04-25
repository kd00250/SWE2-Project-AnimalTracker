import unittest
from datetime import datetime
from model.data.SightingStorage import SightingStorage
from model.Sighting import Sighting


class TestSightingStorageRetrieve(unittest.TestCase):

    def setUp(self):
        self.storage = SightingStorage()

    def test_retrieve_all_sightings_empty(self):
        result = self.storage.retrieve_all_sightings()

        self.assertEqual([], result)

    def test_retrieve_all_sightings(self):
        s1 = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now(), "Notes")
        s2 = Sighting("1235", "user2", "Forest", 5.0, 15.0, datetime.now(), "Notes")

        self.storage.add_sighting(s1)
        self.storage.add_sighting(s2)

        result = self.storage.retrieve_all_sightings()

        self.assertEqual([s1, s2], result)

    def test_retrieve_by_animal_id_one_match(self):
        s1 = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now(), "Notes")
        s2 = Sighting("1254", "user2", "Forest", 5.0, 15.0, datetime.now(), "Notes")

        self.storage.add_sighting(s1)
        self.storage.add_sighting(s2)

        result = list(self.storage.retrieve_sightings_by_animal_id("1234"))

        self.assertEqual([s1], result)

    def test_retrieve_by_animal_id_multiple_matches(self):
        s1 = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now(), "Notes")
        s2 = Sighting("1234", "user2", "Forest", 5.0, 15.0, datetime.now(), "Notes")

        self.storage.add_sighting(s1)
        self.storage.add_sighting(s2)

        result = list(self.storage.retrieve_sightings_by_animal_id("1234"))

        self.assertEqual([s1, s2], result)

    def test_retrieve_by_animal_id_no_match(self):
        s1 = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now(), "Notes")

        self.storage.add_sighting(s1)

        result = list(self.storage.retrieve_sightings_by_animal_id("9999"))

        self.assertEqual([], result)


if __name__ == "__main__":
    unittest.main()