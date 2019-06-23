const SERVER_URL = `${process.env.REACT_APP_SERVER_URL}`;
const SERVER_PORT = `${process.env.REACT_APP_SERVER_PORT}`;
const API_CONTEXT = `${process.env.REACT_APP_API_BASE_URL}`;
const API_VERSION = `${process.env.REACT_APP_API_VERSION}`;
const TASKS_API = `${process.env.REACT_APP_API_TASKS}`;

const API_BASE_URL = `${SERVER_URL}:${SERVER_PORT}${API_CONTEXT}/${API_VERSION}`;

const TASKS_API_URL = `${API_BASE_URL}${TASKS_API}`;

export default TASKS_API_URL;
