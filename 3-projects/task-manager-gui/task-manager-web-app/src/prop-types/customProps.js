import moment from 'moment';

const STRICT = true;
const MANDATORY = true;

const validateDateString = (props, propName, componentName, format, mandatory) => {
  const dateString = props[propName];
  if (mandatory && !(propName in props)) {
    return Error(`Missing ${propName}.`);
  }
  if (!moment(dateString, format, STRICT).isValid()) {
    return Error(`Invalid prop ${propName} passed to ${componentName}. Expected a date of the format ${format}.`);
  }
};

const CustomProps = {
  localDateString: (props, propName, componentName) =>
    validateDateString(props, propName, componentName, 'YYYY-MM-DD', MANDATORY),
  localTimeString: (props, propName, componentName) =>
    validateDateString(props, propName, componentName, 'HH:mm', MANDATORY),
};

export default CustomProps;
