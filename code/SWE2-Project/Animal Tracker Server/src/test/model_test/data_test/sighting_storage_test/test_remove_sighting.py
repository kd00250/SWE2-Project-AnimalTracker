import unittest
from datetime import datetime
from model.data.SightingStorage import SightingStorage
from model.Sighting import Sighting


class TestSightingStorageRemove(unittest.TestCase):

    def test_remove_existing_sighting(self):
        storage = SightingStorage()
        sighting = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now(), "Notes")
        storage.add_sighting(sighting)

        result = storage.remove_sighting(sighting)

        self.assertTrue(result)
        self.assertEqual([], storage.retrieve_all_sightings())

    def test_remove_missing_sighting(self):
        storage = SightingStorage()
        sighting = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now(), "Notes")

        result = storage.remove_sighting(sighting)

        self.assertFalse(result)


if __name__ == "__main__":
    unittest.main()