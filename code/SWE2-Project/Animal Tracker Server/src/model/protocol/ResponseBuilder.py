from model.Role import Role


class ResponseBuilder:
    """
    Response builder class.
    Builds responses based on the type of request
    """

    @staticmethod
    def build_login_response(has_token):
        """
        Builds the response for a login request.
        :param has_token: Authentication token if login succeeds
        :return: JSON - Response object generated based on the request
        """
        if has_token:
            response = {
                "token": has_token
            }
        else:
            response = {
                "status": "error",
            }

        return response

    @staticmethod
    def build_get_role_response(has_role):
        """
        Builds the response for get role request.
        :param has_role: Role of the user
        :return: JSON - Response containing role or error status
        """
        if has_role:
            response = {
                "role": has_role
            }
            print(response)
        else:
            response = {
                "status": "error",
            }
        return response

    @staticmethod
    def build_add_user_response(user):
        """
        Builds the response for add user request.
        :param user: User object that was added
        :return: JSON - Response object generated based on the request
        """
        if user is not None:
            response = {
                "status": "success",
            }
        else:
            response = {
                "status": "error",
            }
        return response

    @staticmethod
    def build_user_exists_response():
        """
        Builds the response for user exists request.
        :return: JSON - Error response
        """
        response = {
            "status": "error",
        }
        return response

    @staticmethod
    def build_retrieved_projects_response(projects):
        """
        Builds the response for retrieved projects request.
        :param projects: Collection of project objects
        :return: JSON - Response object generated based on the request
        """
        if projects is None:
            response = {
                "status": "error",
                "projects": []
            }
            return response

        project_list = []
        for project in projects:
            project_list.append({
                "name": project.get_name(),
                "id": project.get_id(),
            })
        response = {
            "projects": project_list
        }
        return response

    @staticmethod
    def build_user_does_not_have_permission_response():
        """
        Builds the response for user does not have a permission request.
        :return: JSON - Error response
        """
        response = {
            "status": "error",
        }
        return response

    @staticmethod
    def build_could_not_find_project():
        """
        Builds the response for could not find project request.
        :return: JSON - Error response
        """
        response = {
            "status": "error",
        }
        return response

    @staticmethod
    def build_get_project_response(project):
        """
        Builds the response for get project request.
        :param project: Project object containing animal data
        :return: JSON - Response containing an animal list
        """
        animal_list = []
        animals = project.get_animals()
        if animals is None:
            animal_list.append({
                "Class": "",
                "Height": "",
                "Weight": "",
                "Length": "",
                "TagID": "",
                "Description": "",
            })
        else:
            for animal in animals:
                animal_list.append({
                    "Class": animal.get_animal_class().name,
                    "Height": animal.get_height(),
                    "Weight": animal.get_weight(),
                    "Length": animal.get_length(),
                    "TagID": animal.get_tag_id(),
                    "Description": animal.get_description(),
                })
        response = {
            "animals": animal_list
        }
        return response

    @staticmethod
    def build_removed_project(has_removed_project):
        """
        Builds the response for removed project request.
        :param has_removed_project: Boolean indicating whether the project
        :return: JSON - Success or Error
        """
        if has_removed_project:
            response = {
                "status": "success",
            }
            return response
        else:
            response = {
                "status": "error",
            }
            return response

    @staticmethod
    def build_token_does_not_exist():
        """
        Builds the response for token does not exist request.
        :return: JSON - Error response
        """
        response = {
            "status": "error-TokenDoesNotExist",
        }
        return response

    @staticmethod
    def build_get_scientist_response(users, project_creator):
        """
        Builds the response for get scientist request.
        :param users: Collection of user objects
        :param project_creator: User who created the project
        :return: JSON - Response containing a filtered user list
        """
        user_list = []

        for user in users:
            if user.get_role() == Role.SCIENTIST and user.get_username() != project_creator.get_username():
                user_list.append({
                    "username": user.get_username(),
                    "password": "******",
                })

        response = {
            "users": user_list
        }
        return response

    @staticmethod
    def build_create_project_response(created_project):
        """
        Builds the response for create project request.
        :param created_project: Integer project id or None
        :return: JSON - Success or Error
        """
        if created_project is None:
            response = {
                "status": "error",
            }
        else:
            response = {
                "status": "success",
            }
        return response

    @staticmethod
    def build_add_animal_response(animal):
        """
        Builds the response for add animal request.
        :param animal: Animal object that was added
        :return: JSON - Success or Error
        """
        if animal is None:
            response = {
                "status": "error",
            }
        else:
            response = {
                "status": "success",
            }
        return response

    @staticmethod
    def build_user_is_not_in_system():
        """
        Builds the response for user is not in system request.
        :return: A status error.
        """
        response = {
            "status": "error",
        }
        return response

    @staticmethod
    def build_add_sighting_response(sighting):
        """
        Builds the response for add sighting request.
        :param sighting: Sighting object that was added
        :return: Status depending on if the sighting was added.
        """
        if sighting is None:
            response = {
                "status": "error",
            }
        else:
            response = {
                "status": "success",
            }
        return response

    @staticmethod
    def build_tag_does_not_exist():
        """
        Builds the response for tag does not exist request.
        :return: Status error.
        """
        response = {
            "status": "error",
        }
        return response

    @staticmethod
    def build_get_sighting_response(sightings):
        """
        Builds the response for get sighting request.
        :param sightings: Sightings object that was added.
        :return: Response containing a filtered sighting list.
        """
        sighting_list = []

        if not sightings:
            sighting_list.append({
                "animalTagID": "",
                "username": "",
                "location": "",
                "latitude": "",
                "longitude": "",
                "time": "",
                "notes": "",
            })
        else:
            for sighting in sightings:
                sighting_list.append({
                    "animalTagID": sighting.get_animal_tag(),
                    "username": sighting.get_username(),
                    "location": sighting.get_location(),
                    "latitude": sighting.get_latitude(),
                    "longitude": sighting.get_longitude(),
                    "time": sighting.get_time().isoformat(),
                    "notes": sighting.get_notes(),
                })

        response = {
            "sightings": sighting_list
        }

        return response

    @staticmethod
    def build_invalid_time():
        """
        Builds the response for invalid time request.
        :return: Status error.
        """
        response = {
            "status": "error",
        }
        return response
