package novatec.cg.ema.wiremock.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import novatec.cg.ema.wiremock.R;

@EFragment(R.layout.fragment_get_fancy_task)
public class GetFancyTaskFragment extends Fragment {

    private GetWeatherFragmentInteractionListener listener;

    public static GetFancyTaskFragment newInstance() {
        GetFancyTaskFragment fragment = new GetFancyTaskFragment_();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (GetWeatherFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement GetWeatherFragmentInteractionListener");
        }
    }

    @Click
    void fancyButtonClicked() {
        listener.onFancyButtonClicked();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface GetWeatherFragmentInteractionListener {
        void onFancyButtonClicked();
    }

}
