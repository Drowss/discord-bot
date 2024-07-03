## Hi I'm Bot Drow

This bot handles the following commands:
- `/ayuda` - Displays all the commands
- `/saludar` - Greetings to all
- `/preguntar` - Tag a friend and ask who asked him or her
- `/decir` - Bot says something
- `/golpear` - Tag a friend and hit him or her, it counts the number of hits with Postgres

# Environment Variables <a name="environment-variables"></a>

The application uses environment variables for configuration. These are stored in a `.env` file at the root of the project. You should create your own `.env` file and set the following variables:

```properties
bot_token=your_bot_token
url=your_database_url
db_user=your_db_user
db_password=your_db_password
```