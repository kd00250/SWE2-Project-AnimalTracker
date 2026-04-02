import zmq
import json

from model.Role import Role
from model.User import User
from model.authentication.Authenticator import Authenticator
from model.data.ServerStorage import ServerStorage
from model.protocol.ResponseBuilder import ResponseBuilder


def main():
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind("tcp://127.0.0.1:5555")
    print("Server listening on port 5555...")

    print("Creating User")
    storage = ServerStorage()
    bob = User("Bob", "1234", Role.SCIENTIST)
    storage.add_user(bob)

    while True:
        raw = socket.recv()
        message = raw.decode("utf-8")

        if message == "exit":
            print("Server - Received exit, shutting down.")
            break

        has_token = Authenticator.check_login(message)
        response = ResponseBuilder.build_login_response(has_token)

        socket.send(json.dumps(response).encode("utf-8"))

    socket.close()
    context.term()


if __name__ == "__main__":
    main()