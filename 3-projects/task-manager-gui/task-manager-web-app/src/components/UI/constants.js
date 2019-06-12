const valuesOf = dictionary => {
  return Object.keys(dictionary).map(key => {
    return dictionary[key];
  });
};

export const NOTIFICATION_TYPE = {
  INFO: 'INFO',
  SUCCESS: 'SUCCESS',
  WARNING: 'WARNING',
  ERROR: 'ERROR',
};

export const NOTIFICATION_TYPE_VALUES = valuesOf(NOTIFICATION_TYPE);
