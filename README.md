# SchedulerDemo

First run the `spring-boot-mysql-rest-api` application and add some value in `notes` table using below POST API uri.

http://localhost:8080/api/notes

```
[
    {
        "id": 1,
        "title": "First",
        "content": "First Note",
        "createdAt": "2020-05-10T18:30:00.000+0000",
        "updatedAt": "2020-05-10T18:30:00.000+0000"
    }
]
```

thenafter run the `Scheduler-demo` application.
