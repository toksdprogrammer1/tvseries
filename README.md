# tvseries

For more info please visit Swagger doc on https://global-accelerex-tvseries.herokuapp.com/swagger-ui.html

Base URL: https://global-accelerex-tvseries.herokuapp.com/] 

###Add a new episode

###POST "https://global-accelerex-tvseries.herokuapp.com/episodes"

######Request 
```
{ 
  "episodeCode": "Baker",<br>
  "name": "The Baker and the Beauty",<br>
  "releaseDate": "2020-01-02T13:30:37.873Z"<br>
}
```
######Response body
```
{
  "created": "2020-07-07T13:32:21.402",
  "id": 1,
  "name": "The Baker and the Beauty",
  "releaseDate": "2020-01-02T13:30:37.873",
  "episodeCode": "Baker",
  "commentsCount": 0
}
```

###Get all episodes (Episode list endpoint)

######Request
GET "https://global-accelerex-tvseries.herokuapp.com/episodes"

######Response

```
[
  {
    "created": "2020-07-07T13:52:29",
    "id": 21,
    "name": "Absentia",
    "releaseDate": "2015-07-02T15:30:37",
    "episodeCode": "Absentia",
    "commentsCount": 3
  },
  {
    "created": "2020-07-07T13:51:23",
    "id": 11,
    "name": "Tehran",
    "releaseDate": "2017-06-06T11:30:37",
    "episodeCode": "Tehran",
    "commentsCount": 1
  },
  {
    "created": "2020-07-07T13:32:21",
    "id": 1,
    "name": "The Baker and the Beauty",
    "releaseDate": "2020-01-02T13:30:37",
    "episodeCode": "Baker",
    "commentsCount": 2
  }
]
```

###Add a comment to an episode
POST "https://global-accelerex-tvseries.herokuapp.com/episodes/21/comments"

######Request body

```
{
  "comment": "Awesome"
}
```

######Request body

```
{
  "created": "2020-07-07T13:56:02.803",
  "id": 51,
  "comment": "Awesome",
  "ipAddressLocation": "105.112.43.115",
  "episodeId": 21
}
```

######Get episode comments

######Request
GET "https://global-accelerex-tvseries.herokuapp.com/episodes/1/comments"

###### Response body
```
[
  {
    "created": "2020-07-07T13:54:41",
    "id": 11,
    "comment": "Relly nice",
    "ipAddressLocation": "105.112.43.115",
    "episodeId": 1
  },
  {
    "created": "2020-07-07T13:54:25",
    "id": 1,
    "comment": "Very interesting",
    "ipAddressLocation": "105.112.43.115",
    "episodeId": 1
  }
]
```

### Comment list endpoint

###Request
```
[
  {
    "created": "2020-07-07T13:54:25",
    "id": 1,
    "comment": "Very interesting",
    "ipAddressLocation": "105.112.43.115",
    "episodeId": 1
  },
  {
    "created": "2020-07-07T13:54:41",
    "id": 11,
    "comment": "Relly nice",
    "ipAddressLocation": "105.112.43.115",
    "episodeId": 1
  },
  {
    "created": "2020-07-07T13:54:59",
    "id": 21,
    "comment": "Cool",
    "ipAddressLocation": "105.112.43.115",
    "episodeId": 11
  },
  {
    "created": "2020-07-07T13:55:40",
    "id": 31,
    "comment": "Well, not bad",
    "ipAddressLocation": "105.112.43.115",
    "episodeId": 21
  },
  {
    "created": "2020-07-07T13:55:51",
    "id": 41,
    "comment": "I love it",
    "ipAddressLocation": "105.112.43.115",
    "episodeId": 21
  },
  {
    "created": "2020-07-07T13:56:02",
    "id": 51,
    "comment": "Awesome",
    "ipAddressLocation": "105.112.43.115",
    "episodeId": 21
  }
]
```

###Add a Character

POST "https://global-accelerex-tvseries.herokuapp.com/characters"

######Request body

```
{
  "episodes": [
    1,11
  ],
  "firstName": "Patrick",
  "gender": "MALE",
  "lastName": "Heusinger",
  "location": {
    "latitude": 0.876,
    "longitude": 0.0987,
    "name": "Israel"
  },
  "stateOfOrigin": "United States",
  "status": "ACTIVE"
}
```

######Response Body

```
{
  "created": "2020-07-07T14:16:22.025",
  "id": 11,
  "firstName": "Patrick",
  "lastName": "Heusinger",
  "status": "ACTIVE",
  "stateOfOrigin": "United States",
  "gender": "MALE",
  "location": {
    "name": "Israel",
    "latitude": 0.876,
    "longitude": 0.0987
  },
  "episodeCount": 2
}
```

###Search for a List of Episodes a Character featured in

GET "https://global-accelerex-tvseries.herokuapp.com/characters/21/episodes"

###Response body

```
[
  {
    "created": "2020-07-07T13:51:23",
    "id": 11,
    "name": "Tehran",
    "releaseDate": "2017-06-06T11:30:37",
    "episodeCode": "Tehran",
    "commentsCount": 1
  },
  {
    "created": "2020-07-07T13:52:29",
    "id": 21,
    "name": "Absentia",
    "releaseDate": "2015-07-02T15:30:37",
    "episodeCode": "Absentia",
    "commentsCount": 3
  },
  {
    "created": "2020-07-07T13:32:21",
    "id": 1,
    "name": "The Baker and the Beauty",
    "releaseDate": "2020-01-02T13:30:37",
    "episodeCode": "Baker",
    "commentsCount": 2
  }
]
```

###Character Endpoint sort by firstNme, ascending and gender, descending

######Request
GET "https://global-accelerex-tvseries.herokuapp.com/characters/characters?sort=firstName,ascending&sort=gender,descending"

######Response body
```
[
  {
    "created": "2020-07-07T14:21:48",
    "id": 41,
    "firstName": "Amber",
    "lastName": "Aga",
    "status": "ACTIVE",
    "stateOfOrigin": "Canada",
    "gender": "FEMALE",
    "location": {
      "name": "Spain",
      "latitude": 10.876,
      "longitude": 0.0987
    },
    "episodeCount": 1
  },
  {
    "created": "2020-07-07T14:18:55",
    "id": 21,
    "firstName": "Cara",
    "lastName": "Theobold",
    "status": "ACTIVE",
    "stateOfOrigin": "Canada",
    "gender": "FEMALE",
    "location": {
      "name": "Spain",
      "latitude": 70.876,
      "longitude": 90.0987
    },
    "episodeCount": 3
  },
  {
    "created": "2020-07-07T14:22:55",
    "id": 51,
    "firstName": "Geoff",
    "lastName": "Bell",
    "status": "UNKNOWN",
    "stateOfOrigin": "Canada",
    "gender": "MALE",
    "location": {
      "name": "Israel",
      "latitude": 1.876,
      "longitude": 1.0987
    },
    "episodeCount": 1
  },
  {
    "created": "2020-07-07T14:20:03",
    "id": 31,
    "firstName": "Natasha",
    "lastName": "Little",
    "status": "UNKNOWN",
    "stateOfOrigin": "Canada",
    "gender": "FEMALE",
    "location": {
      "name": "Israel",
      "latitude": 100.876,
      "longitude": 90.0987
    },
    "episodeCount": 1
  },
  {
    "created": "2020-07-07T14:16:22",
    "id": 11,
    "firstName": "Patrick",
    "lastName": "Heusinger",
    "status": "ACTIVE",
    "stateOfOrigin": "United States",
    "gender": "MALE",
    "location": {
      "name": "Israel",
      "latitude": 0.876,
      "longitude": 0.0987
    },
    "episodeCount": 2
  },
  {
    "created": "2020-07-07T14:14:02",
    "id": 1,
    "firstName": "Stana",
    "lastName": "Katic",
    "status": "ACTIVE",
    "stateOfOrigin": "United States",
    "gender": "MALE",
    "location": {
      "name": "Israel",
      "latitude": 0.876,
      "longitude": 0.0987
    },
    "episodeCount": 0
  }
]
```

######Character Endpoint sort by firstNme, ascending and filter by gender

###Request
GET "https://global-accelerex-tvseries.herokuapp.com/characters/characters?gender=FEMALE&sort=firstName,ascending"

###Response body

```
[
  {
    "created": "2020-07-07T14:21:48",
    "id": 41,
    "firstName": "Amber",
    "lastName": "Aga",
    "status": "ACTIVE",
    "stateOfOrigin": "Canada",
    "gender": "FEMALE",
    "location": {
      "name": "Spain",
      "latitude": 10.876,
      "longitude": 0.0987
    },
    "episodeCount": 1
  },
  {
    "created": "2020-07-07T14:18:55",
    "id": 21,
    "firstName": "Cara",
    "lastName": "Theobold",
    "status": "ACTIVE",
    "stateOfOrigin": "Canada",
    "gender": "FEMALE",
    "location": {
      "name": "Spain",
      "latitude": 70.876,
      "longitude": 90.0987
    },
    "episodeCount": 3
  },
  {
    "created": "2020-07-07T14:20:03",
    "id": 31,
    "firstName": "Natasha",
    "lastName": "Little",
    "status": "UNKNOWN",
    "stateOfOrigin": "Canada",
    "gender": "FEMALE",
    "location": {
      "name": "Israel",
      "latitude": 100.876,
      "longitude": 90.0987
    },
    "episodeCount": 1
  }
]
```

######Character Endpoint sort by firstNme, ascending and filter by gender and status

###Request
https://global-accelerex-tvseries.herokuapp.com/characters/characters?gender=MALE&sort=firstName,ascending&status=UNKNOWN"

###Response body

```
 [
   {
     "created": "2020-07-07T14:22:55",
     "id": 51,
     "firstName": "Geoff",
     "lastName": "Bell",
     "status": "UNKNOWN",
     "stateOfOrigin": "Canada",
     "gender": "MALE",
     "location": {
       "name": "Israel",
       "latitude": 1.876,
       "longitude": 1.0987
     },
     "episodeCount": 1
   }
 ]
 ```