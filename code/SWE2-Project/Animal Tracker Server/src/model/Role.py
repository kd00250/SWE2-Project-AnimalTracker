import enum


class Role(enum.Enum):
    """
    The possible roles for a user.
    """
    ADMIN = 0
    SCIENTIST = 1
    CONTRIBUTOR = 2
    GUEST = 3
