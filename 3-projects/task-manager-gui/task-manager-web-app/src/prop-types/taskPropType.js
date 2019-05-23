import PropTypes from 'prop-types';
import CustomProps from './customProps';
import { TASK_PRIORITY_VALUES, TASK_STATUS_VALUES } from '../common/utils/taskUtils';

const task = {
  id: PropTypes.number,
  summary: PropTypes.string.isRequired,
  description: PropTypes.string,
  priority: PropTypes.oneOf(TASK_PRIORITY_VALUES).isRequired,
  date: CustomProps.localDateString,
  time: CustomProps.localTimeString,
  status: PropTypes.oneOf(TASK_STATUS_VALUES).isRequired,
};

export default PropTypes.shape(task);
