import unittest
from datetime import datetime, timedelta
from model.Sighting import Sighting


class TestSightingConstructor(unittest.TestCase):

    def test_animal_tag_none(self):
        with self.assertRaises(Exception):
            Sighting(None, "user", "Park", 10.0, 20.0, datetime.now(), "Notes")

    def test_username_none(self):
        with self.assertRaises(Exception):
            Sighting("1234", None, "Park", 10.0, 20.0, datetime.now(), "Notes")

    def test_location_none(self):
        with self.assertRaises(Exception):
            Sighting("1234", "user", None, 10.0, 20.0, datetime.now(), "Notes")

    def test_location_empty(self):
        with self.assertRaises(Exception):
            Sighting("1234", "user", "   ", 10.0, 20.0)

    def test_invalid_latitude_low(self):
        with self.assertRaises(Exception):
            Sighting("1234", "user", "Park", -91.0, 20.0, datetime.now(), "Notes")

    def test_invalid_latitude_high(self):
        with self.assertRaises(Exception):
            Sighting("1234", "user", "Park", 91.0, 20.0, datetime.now(), "Notes")

    def test_invalid_longitude_low(self):
        with self.assertRaises(Exception):
            Sighting("1234", "user", "Park", 10.0, -181.0, datetime.now(), "Notes")

    def test_invalid_longitude_high(self):
        with self.assertRaises(Exception):
            Sighting("1234", "user", "Park", 10.0, 181.0, datetime.now(), "Notes")

    def test_invalid_time_empty(self):
        with self.assertRaises(Exception):
            Sighting("1234", "user", "Park", 10.0, 20.0, None, "   ")

    def test_invalid_notes_empty(self):
        with self.assertRaises(Exception):
            Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now(), "   ")

    def test_valid_constructor_with_all_fields(self):
        time = datetime.now() - timedelta(hours=1)
        sighting = Sighting("1234", "user", "Park", 10.0, 20.0, time, "Near lake")

        self.assertEqual("1234", sighting.get_animal_tag())
        self.assertEqual("user", sighting.get_username())
        self.assertEqual("Park", sighting.get_location())
        self.assertEqual(10.0, sighting.get_latitude())
        self.assertEqual(20.0, sighting.get_longitude())
        self.assertEqual(time, sighting.get_time())
        self.assertEqual("Near lake", sighting.get_notes())

    def test_boundary_latitude(self):
        sighting1 = Sighting("1234", "user", "Loc", -90.0, 0.0, datetime.now(), "Near lake")
        sighting2 = Sighting("1234", "user", "Loc", 90.0, 0.0, datetime.now(), "Near lake")

        self.assertEqual(-90.0, sighting1.get_latitude())
        self.assertEqual(90.0, sighting2.get_latitude())

    def test_boundary_longitude(self):
        sighting1 = Sighting("1234", "user", "Loc", 0.0, -180.0, datetime.now(), "Near lake")
        sighting2 = Sighting("1234", "user", "Loc", 0.0, 180.0, datetime.now(), "Near lake")

        self.assertEqual(-180.0, sighting1.get_longitude())
        self.assertEqual(180.0, sighting2.get_longitude())


if __name__ == "__main__":
    unittest.main()
