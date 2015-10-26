package novatec.cg.ema.wiremock.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import novatec.cg.ema.wiremock.R;

@EFragment(R.layout.fragment_delete_task)
public class DeleteTaskFragment extends Fragment {

    @ViewById
    EditText taskId;

    private OnFragmentInteractionListener mListener;

    public static DeleteTaskFragment newInstance() {
        DeleteTaskFragment fragment = new DeleteTaskFragment_();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Click
    void removeTaskButtonClicked() {
        mListener.onRemoveTask(taskId.getText().toString());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onRemoveTask(String id);
    }

}
