import argparse
import sys
from pymongo import MongoClient


def generate(args):
    username = args.name
    password = args.password
    client = MongoClient("mongodb+srv://" + username + ":" + password +
                         "@cluster0-vyuoj.mongodb.net/test?retryWrites=true&w=majority")
    db = client['TicketAgency']

    # events
    collection = db['events']
    # TD Garden
    event = {
        'name': "TD Garden",
        'type': "Ball Game",
        'rest_tickets': {
            'vip': 50,
            'floor1': 5000,
            'floor2': 10000,
            'floor3': 15000,
            'balcony': 29950
        }
    }
    tickets = []
    for i in range(50000):
        if i < 50:
            tickets.append({
                'ticket_id': 'TDGVIP' + str(i).zfill(5),
                'ticket_type': 'vip',
                'price': 2000.0,
                'sold': False
            })
        elif i < 5050:
            tickets.append({
                'ticket_id': 'TDGFL1' + str(i).zfill(5),
                'ticket_type': 'floor1',
                'price': 1000.0,
                'sold': False
            })
        elif i < 15050:
            tickets.append({
                'ticket_id': 'TDGFL2' + str(i).zfill(5),
                'ticket_type': 'floor2',
                'price': 500.0,
                'sold': False
            })
        elif i < 30050:
            tickets.append({
                'ticket_id': 'TDGFL3' + str(i).zfill(5),
                'ticket_type': 'floor3',
                'price': 200.0,
                'sold': False
            })
        else:
            tickets.append({
                'ticket_id': 'TDGBAL' + str(i).zfill(5),
                'ticket_type': 'balcony',
                'price': 100.0,
                'sold': False
            })
    event['tickets'] = tickets
    collection.insert_one(event)

    # BSO
    event = {
        'name': "Boston Symphony Orchestra",
        'type': "Concert",
        'rest_tickets': {
            'vip': 50,
            'floor1': 4000,
            'floor2': 6000,
            'balcony': 9950
        }
    }
    tickets = []
    for i in range(20000):
        if i < 50:
            tickets.append({
                'ticket_id': 'BSOVIP' + str(i).zfill(5),
                'ticket_type': 'vip',
                'price': 1000.0,
                'sold': False
            })
        elif i < 4050:
            tickets.append({
                'ticket_id': 'BSOFL1' + str(i).zfill(5),
                'ticket_type': 'floor1',
                'price': 500.0,
                'sold': False
            })
        elif i < 10050:
            tickets.append({
                'ticket_id': 'BSOFL2' + str(i).zfill(5),
                'ticket_type': 'floor2',
                'price': 250.0,
                'sold': False
            })
        else:
            tickets.append({
                'ticket_id': 'BSOBAL' + str(i).zfill(5),
                'ticket_type': 'balcony',
                'price': 100.0,
                'sold': False
            })
    event['tickets'] = tickets
    collection.insert_one(event)

    # MFA
    event = {
        'name': "Museum of Fine Arts",
        'type': "Exhibition",
        'rest_tickets': {
            'vip': 50,
            'standard': 950
        },
        'tickets': {
            'vip': {
                'amount': 50,
                'price': 50
            },
            'standard': {
                'amount': 950,
                'price': 20
            }
        }
    }
    collection.insert_one(event)

    # users
    collection = db['users']
    users = []
    for i in range(25000):
        users.append({
            'name': "user" + str(i).zfill(5),
            'password': "123456",
            'tickets': []
        })
    collection.insert_many(users)
    client.close()


def main():
    parser = argparse.ArgumentParser(description='Generate Data to MongoDB Atlas')
    parser.add_argument('-n', '--name', required=True, help='MongoDB Atlas User Name')
    parser.add_argument('-p', '--password', required=True, help='MongoDB Atlas Password')
    parser.set_defaults(func=generate)
    args = parser.parse_args(sys.argv[1:])
    args.func(args)


if __name__ == "__main__":
    main()
