import { connect } from 'react-redux';
import TasksOverview from './TasksOverview';
import { fetchAllTasks } from '../../../redux/actions/tasksActions';
import { showNotificationBox } from '../../../redux/actions/uiActions';

const mapStateToProps = state => {
  return {
    tasks: state.tasks.content,
  };
};

const mapDispatchToProps = dispatch => {
  return {
    fetchTasks: () => dispatch(fetchAllTasks()),
    showNotificationBox: message => dispatch(showNotificationBox(message)),
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TasksOverview);
