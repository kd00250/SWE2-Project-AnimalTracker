class ResponseBuilder:

    @staticmethod
    def build_login_response(has_token):
        if has_token:
            print("Server - Received login, sending request to client...")
            response = {
                "token": has_token
            }
        else:
            print("Server - Login Failed")
            response = {
                "status": "error",
            }

        return response

    @staticmethod
    def build_get_role_response(has_role):
        if has_role:
            print("Server - Received get role, sending request to client...")
            print(has_role)
            print(type(has_role))
            response = {
                "role": has_role
            }
            print(response)
        else:
            print("Server - Get role Failed")
            response = {
                "status": "error",
            }
        return response

    @staticmethod
    def build_add_user_response(user):
        if user is not None:
            print("Server - Received add user, sending request to client...")
            response = {
                "status": "success",
            }
        else:
            print("Server - Add user Failed")
            response = {
                "status": "error",
            }
        return response

    @staticmethod
    def build_user_exists_response():
        print("Server - User exists Failed")
        response = {
            "status": "error",
        }
        return response

    @staticmethod
    def build_retrieved_projects_response(projects):
        print("Server - Received retrieved projects, sending request to client...")

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
        print("Server - Has no Permission")
        response = {
            "status": "error",
        }
        return response

    @staticmethod
    def build_could_not_find_project():
        print("Server - Could not find project")
        response = {
            "status": "error",
        }
        return response

    @staticmethod
    def build_get_project_response(project):
        animal_list = []
        animals = project.get_animals()
        for animal in animals:
            animal_list.append({
                "Class": animal.animal_class.name,
                "Height": animal.animal_height,
                "Weight": animal.animal_weight,
                "Length": animal.animal_length,
                "TagID": animal.tag_id,
                "Description": animal.description,
            })
        response = {
            "projects": animal_list
        }
        return response



