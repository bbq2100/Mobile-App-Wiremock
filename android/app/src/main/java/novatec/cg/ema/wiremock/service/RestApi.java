package novatec.cg.ema.wiremock.service;


import java.util.List;

import novatec.cg.ema.wiremock.model.Task;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface RestApi {

    @GET("/tasks")
    void getTasks(Callback<List<Task>> tasks);

    @POST("/tasks")
    void createTask(@Body Task task, Callback<Response> response);

    @DELETE("/tasks/{id}")
    void deleteTask(@Path("id") String id, Callback<Response> response);

    @GET("/fancy/task")
    void getFancyTask(Callback<Task> fancyTaskCallback);
}
