# OpenSearch Migration RFC

## Running


You'll need a java JDK to run, think I got 17 https://www.oracle.com/java/technologies/downloads/#jdk20-mac

a bit shitty right now because I haven't set up the project very well, but can add migrations at the momeny and run them.
Toss a migration in the migrations (or migration) folder in `static/es`, and run the app. Its a web based app becaused I used spring 
boot for easiest setup (also recommended by the migration library, but probably dind't need the web component), so running by entering the top level and running the command

`./mvnw spring-boot:run`

will run the app, which will run the migrations. I honestly have no idea why it does that without having to run

`curl localhost:8080/migrate`

but can't be fucked to resolve for rfc


## Migration Format

This is configurable, but right now its default, file name format is

`V<x.x>__<descritption>.http`

and format shoudl be 

```
<METHOD> <EDNPOINT>
Content-Type: application/json

<CONTENT>
```
