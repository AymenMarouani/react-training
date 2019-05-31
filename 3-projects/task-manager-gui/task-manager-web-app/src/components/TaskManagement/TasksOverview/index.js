import { connect } from 'react-redux';
import TasksOverview from './TasksOverview';
import { fetchAllTasks } from '../../../redux/actions/tasksActions';

const mapStateToProps = state => {
  return {
    tasks: state.tasks.content,
  };
};

const mapDispatchToProps = dispatch => {
  return {
    fetchTasks: () => dispatch(fetchAllTasks()),
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TasksOverview);
