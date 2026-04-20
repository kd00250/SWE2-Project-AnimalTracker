import unittest
from datetime import datetime
from model.data.SightingStorage import SightingStorage
from model.Sighting import Sighting


class TestSightingStorageReset(unittest.TestCase):

    def test_reset(self):
        storage = SightingStorage()
        s1 = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now())
        s2 = Sighting("1235", "user2", "Forest", 5.0, 15.0, datetime.now())

        storage.add_sighting(s1)
        storage.add_sighting(s2)

        storage.reset()

        self.assertEqual([], storage.retrieve_all_sightings())


if __name__ == "__main__":
    unittest.main()