package novatec.cg.ema.wiremock.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import novatec.cg.ema.wiremock.R;
import novatec.cg.ema.wiremock.model.Task;

@EFragment(R.layout.fragment_create_task)
public class CreateTaskFragment extends Fragment {

    @ViewById
    EditText taskTitle;

    @ViewById
    EditText taskContent;

    private CreateTaskFragmentInteractionListener listener;

    public static CreateTaskFragment newInstance() {
        CreateTaskFragment fragment = new CreateTaskFragment_();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (CreateTaskFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Click
    void saveButtonClicked() {
        Task task = new Task();
        task.setContent(taskContent.getText().toString());
        task.setTitle(taskTitle.getText().toString());
        listener.onCreateTask(task);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface CreateTaskFragmentInteractionListener {
        void onCreateTask(Task task);
    }

}
