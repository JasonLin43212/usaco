"""
ID: jasonli7
LANG: PYTHON3
PROG: gift1
"""
def get_exchanges(data):
    # name, money, recievers
    mode = "name"
    exchanges = []
    exchange = {}
    for datum in data:
        if mode == "name":
            exchange["giver"] = datum
            mode = "money"

        elif mode == "money":
            amt, recievers = datum.split(" ")
            amt = int(amt)
            recievers = int(recievers)
            exchange["money_amt"] = amt
            exchange["money_give"] = amt // recievers if recievers != 0 else 0
            exchange["money_save"] = amt % recievers if recievers != 0 else 0
            exchange["num_recievers"] = recievers
            exchange["recievers"] = []

            if recievers == 0:
                mode = "name"
            else:
                mode = "recievers"

        elif mode == "recievers":
            num_recievers = len(exchange["recievers"])
            expected_recievers = exchange["num_recievers"]
            if num_recievers < expected_recievers:
                exchange["recievers"].append(datum)

            if num_recievers == expected_recievers - 1:
                exchanges.append(exchange)
                exchange = {}
                mode = "name"
    return exchanges

def get_final_money(exchanges, people):
    net_values = {person: 0 for person in people}
    for exchange in exchanges:
        net_values[exchange["giver"]] -= exchange["money_amt"] - exchange["money_save"]
        for recievers in exchange["recievers"]:
            net_values[recievers] += exchange["money_give"]
    return net_values

with open('gift1.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

np = int(data[0])
people = data[1:np+1]

exchanges = get_exchanges(data[np+1:])
final_money = get_final_money(exchanges, people)

output = ''
for i, person in enumerate(people):
    output += f'{person} {final_money[person]}'
    if i < len(people) - 1:
        output += '\n'

with open('gift1.out', 'w') as fout:
    fout.write(output + '\n')

#NOTE:
"""
Use a class to make stuff easier
Do the computation at the time of processing in the data
"""
