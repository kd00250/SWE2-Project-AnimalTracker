import unittest
from datetime import datetime
from model.data.SightingStorage import SightingStorage
from model.Sighting import Sighting


class TestSightingStorageAdd(unittest.TestCase):

    def test_add_sighting_none(self):
        storage = SightingStorage()
        with self.assertRaises(Exception):
            storage.add_sighting(None)

    def test_add_valid_sighting(self):
        storage = SightingStorage()
        sighting = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now())

        result = storage.add_sighting(sighting)

        self.assertTrue(result)
        self.assertEqual([sighting], storage.retrieve_all_sightings())

    def test_add_duplicate_sighting(self):
        storage = SightingStorage()
        sighting = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now())

        storage.add_sighting(sighting)
        result = storage.add_sighting(sighting)

        self.assertTrue(result)
        self.assertEqual(1, len(storage.retrieve_all_sightings()))


if __name__ == "__main__":
    unittest.main()