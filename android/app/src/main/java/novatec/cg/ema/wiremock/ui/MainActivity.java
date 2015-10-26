package novatec.cg.ema.wiremock.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.widget.FrameLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import novatec.cg.ema.wiremock.R;
import novatec.cg.ema.wiremock.model.Task;
import novatec.cg.ema.wiremock.service.RestApi;
import novatec.cg.ema.wiremock.service.RestService;
import novatec.cg.ema.wiremock.ui.NavigationDrawerFragment.DrawerMenuItems;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        CreateTaskFragment.CreateTaskFragmentInteractionListener,
        DeleteTaskFragment.OnFragmentInteractionListener,
        TaskListFragment.OnTaskListFragmentInteraction,
        GetFancyTaskFragment.GetWeatherFragmentInteractionListener{

    @FragmentById(R.id.navigation_drawer)
    NavigationDrawerFragment mNavigationDrawerFragment;

    @ViewById
    FrameLayout fragmentContainer;

    private FragmentManager fragmentManager;
    private RestApi restBackend;

    @AfterViews
    void init() {
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        restBackend = RestService.getBackend();
    }

    @Override
    public void onNavigationDrawerItemSelected(final int position) {
        switch (position) {
            case DrawerMenuItems.GET_TASK_LIST:
                inflateFragment(TaskListFragment.newInstance());
                break;
            case DrawerMenuItems.CREATE_TASK:
                inflateFragment(CreateTaskFragment.newInstance());
                break;
            case DrawerMenuItems.DELETE_TASK:
                inflateFragment(DeleteTaskFragment.newInstance());
                break;
            case DrawerMenuItems.GET_FANCY_TASK:
                inflateFragment(GetFancyTaskFragment.newInstance());
                break;
            default:
                throw new IllegalArgumentException("There is no associated DrawerMenu ClickListener existing!");
        }
    }

    private void inflateFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, BundleKeys.ACTIVE_FRAGMENT)
                .commit();
    }

    /**
     * Will return the lastly added and active Fragment inside FragmentManager.
     * The inferred type parameter ensures that the caller will
     * receive an already casted target Fragment.
     */
    @SuppressWarnings("unchecked")
    private <DomainFragment extends Fragment> DomainFragment getActiveDomainFragment() {
        return (DomainFragment) fragmentManager.findFragmentByTag(BundleKeys.ACTIVE_FRAGMENT);
    }


    /**
     * *******************
     * Fragment interaction
     * *******************
     */
    @Override
    public void onCreateTask(Task task) {
        CroutonUtil.showInfo(this, "Bitte warten...");

        restBackend.createTask(task, new Callback<Response>() {
            @Override
            public void success(Response task, Response response) {
                CroutonUtil.showSuccess(MainActivity.this, "Created ;)");
            }

            @Override
            public void failure(RetrofitError error) {
                CroutonUtil.showError(MainActivity.this);
            }
        });
    }

    @Override
    public void onFetchButtonClick() {
        CroutonUtil.showInfo(this, "Bitte warten...");

        final TaskListFragment taskListFragment = getActiveDomainFragment();
        restBackend.getTasks(new Callback<List<Task>>() {
            @Override
            public void success(List<Task> tasks, Response response) {
                CroutonUtil.showSuccess(MainActivity.this, "Ergebnis erhalten...");
                taskListFragment.updateUi(tasks);
            }

            @Override
            public void failure(RetrofitError error) {
                CroutonUtil.showError(MainActivity.this);
            }
        });
    }

    @Override
    public void onRemoveTask(String id) {
        CroutonUtil.showInfo(this, "Bitte warten...");

        restBackend.deleteTask(id, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                CroutonUtil.showSuccess(MainActivity.this, "Entfernt!");
            }

            @Override
            public void failure(RetrofitError error) {
                CroutonUtil.showError(MainActivity.this);
            }
        });
    }

    @Override
    public void onFancyButtonClicked() {
        CroutonUtil.showInfo(this, "Bitte warten...");

        restBackend.getFancyTask(new Callback<Task>() {
            @Override
            public void success(Task task, Response response) {
                CroutonUtil.showSuccess(MainActivity.this, String.format("Fancy result: %s", task));
            }

            @Override
            public void failure(RetrofitError error) {
                CroutonUtil.showError(MainActivity.this);
            }
        });
    }


    /**
     * Configuration
     */
    private final class BundleKeys {
        private static final String ACTIVE_FRAGMENT = "ACTIVE_FRAGMENT";
    }
}
