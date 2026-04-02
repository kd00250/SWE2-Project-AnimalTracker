from model.data.ServerStorage import ServerStorage


class ResponseBuilder:

    @staticmethod
    def build_login_response(has_token):
        storage = ServerStorage()
        if has_token:
            print("Server - Received login, sending request to server...")
            response = {
                "role": str(storage.get_user(has_token).get_role()),
                "token": has_token,
            }
        else:
            print("Server - Login Failed")
            response = {
                "status": "error",
            }
            print(response)

        return response
