import unittest

from model.Animal import Animal
from model.AnimalClass import AnimalClass
from model.TagStatus import TagStatus


class TestConstructor(unittest.TestCase):
    def test_animal_class_none(self):
        with self.assertRaises(Exception):
            Animal(None, 10, 20, 30, 40, "lorem")

    def test_description_none(self):
        with self.assertRaises(Exception):
            Animal(AnimalClass.BIRD, 10, 20, 30, 40, None)

    def test_height_negative(self):
        with self.assertRaises(Exception):
            Animal(AnimalClass.BIRD, -1, 20, 30, 40, "1234")

    def test_width_negative(self):
        with self.assertRaises(Exception):
            Animal(AnimalClass.BIRD, 1, -1, 30, 40, "1234")

    def test_length_negative(self):
        with self.assertRaises(Exception):
            Animal(AnimalClass.BIRD, 10, 20, -1, 40, "1234")

    def test_id_negative(self):
        with self.assertRaises(Exception):
            Animal(AnimalClass.BIRD, 10, 20, 10, -1, "1234")

    def test_valid_animal(self):
        animal = Animal(AnimalClass.BIRD, 10, 20, 10, 10, "lorem")
        self.assertEqual(AnimalClass.BIRD, animal.get_animal_class())
        self.assertEqual(10, animal.get_height())
        self.assertEqual(20, animal.get_weight())
        self.assertEqual(10, animal.get_length())
        self.assertEqual(10, animal.get_tag_id())
        self.assertEqual("lorem", animal.get_description())
        self.assertEqual(TagStatus.ACTIVE, animal.get_tag_status())


if __name__ == '__main__':
    unittest.main()
