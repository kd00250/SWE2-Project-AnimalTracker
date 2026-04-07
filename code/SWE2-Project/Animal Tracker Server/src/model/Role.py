import enum

class Role(enum.Enum):
    ADMIN = 0
    SCIENTIST = 1
    CONTRIBUTOR = 2
    GUEST = 3