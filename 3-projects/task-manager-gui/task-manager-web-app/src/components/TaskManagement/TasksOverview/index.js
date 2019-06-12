import { connect } from 'react-redux';
import TasksOverview from './TasksOverview';
import { fetchAllTasks, showErrorNotification, showSuccessNotification } from '../../../redux/actions';

const mapStateToProps = state => {
  return {
    tasks: state.tasks.content,
  };
};

const mapDispatchToProps = dispatch => {
  return {
    fetchTasks: () => dispatch(fetchAllTasks()),
    showErrorNotification: message => dispatch(showErrorNotification(message)),
    showSuccessNotification: message => dispatch(showSuccessNotification(message)),
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TasksOverview);
