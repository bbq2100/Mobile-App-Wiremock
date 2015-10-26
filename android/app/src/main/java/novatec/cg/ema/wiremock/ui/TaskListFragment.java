package novatec.cg.ema.wiremock.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import novatec.cg.ema.wiremock.R;
import novatec.cg.ema.wiremock.model.Task;

@EFragment(R.layout.fragment_task_list)
public class TaskListFragment extends Fragment {

    @ViewById
    GridView taskGridView;
    private OnTaskListFragmentInteraction listener;
    private TaskGridViewAdapter adapter;

    public static TaskListFragment newInstance() {
        TaskListFragment fragment = new TaskListFragment_();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnTaskListFragmentInteraction) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @AfterViews
    void init() {
        adapter = new TaskGridViewAdapter(this.getActivity());
        taskGridView.setAdapter(adapter);
    }

    @Click
    void buttonFetchClicked() {
        listener.onFetchButtonClick();
    }

    public void updateUi(List<Task> tasks) {
        adapter.add(tasks);
    }

    public interface OnTaskListFragmentInteraction {
        void onFetchButtonClick();
    }

    private static class TaskGridViewAdapter extends BaseAdapter {

        private List<Task> tasks = new ArrayList<>();
        private Context context;

        private TaskGridViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return tasks.size();
        }

        @Override
        public Object getItem(int position) {
            return tasks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            Task task = tasks.get(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.view_task_grid_item, null);
                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.taskTitle);
                viewHolder.content = (TextView) convertView.findViewById(R.id.taskContent);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.title.setText(task.getTitle());
            viewHolder.content.setText(task.getContent());

            return convertView;
        }

        public void add(List<Task> tasks) {
            this.tasks.clear();
            this.tasks.addAll(tasks);
            notifyDataSetChanged();
        }

        private static class ViewHolder {
            TextView title;
            TextView content;
        }
    }

}
