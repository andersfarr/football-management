import os
import pandas as pd
import requests
import json
import math

url = "http://localhost:8080"
team_url = f"{url}/teams"
headers = {"Content-Type": "application/json"}
#Add more URLs
raw_team_df = pd.read_csv("CSV's/Pro_Football_Teams.csv")

team_df = raw_team_df.get(raw_team_df['To'] == 2023)

team_names = team_df['Tm']

team_dict = {}

for name in team_names:
    team_data ={}
    if(not name in team_dict.keys()):
        team_dict[name] = 1
        name_info = name.split()
        if(len(name_info) > 2):
            team_data['name'] = name_info[2]
            team_data['city'] = f"{name_info[0]} {name_info[1]}"
        else:
            team_data['name'] = name_info[1]
            team_data['city'] = name_info[0]
        r = requests.post(team_url, json=team_data, headers = headers)
        print(team_data['name'])
        player_data  = pd.read_csv(f"CSV's/Team Rosters/{team_data['name']}/Roster.csv");
        for player in player_data.iterrows():
            player_json = {}
            name = player[1]['Player']
            if(name.split()[-1][0] == '('):
                name = " ".join(name.split()[0:-1])
            if name == "Team Total":
                break
            print(name)
            player_json["name"] = name
            if(math.isnan(player[1]["No."])):
                player_json["number"] = -1
            else:
                player_json["number"] = int(player[1]["No."])
            player_json["age"] = int(player[1]["Age"])
            player_json["position"] = player[1]["Pos"]
            print(player_json)
            r = requests.post(f"{team_url}/{team_data['name']}/players", json=player_json, headers=headers)
            print(r.status_code)
        
        
    
        