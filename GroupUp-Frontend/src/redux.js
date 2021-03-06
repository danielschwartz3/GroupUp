import { createStore } from 'redux';

const initialState = {
    user: {
        userRole: '',
        userName: '',
        name: '',
        email: '',
        userInstitution: ''
    },

    registeredCourses: [],
    focusedConversation: -1,
    conversations: [],
    courses: [],
};

export const store = createStore(
  reducer,
  initialState,
  window.devToolsExtension && window.devToolsExtension()
);

// Reducer
function reducer(state, action) {
  switch (action.type) {
    case 'INITIALIZE_USER':
        return {
            ...state,
            user: action.payload
        };
    case 'UNINITIALIZE_USER':
        return {
            ...state,
            user: {
                email: '',
                name: ''
            }
        };
    case 'INITIALIZE_USER_COURSES':
        return {
            ...state,
            registeredCourses: action.payload
        };
    case 'GET_COURSES':
        return {
            ...state,
            courses: action.payload
        };
    case 'CREATE_COURSE':
        return {
            ...state,
            courses: [...state.courses, action.payload]
        };
    case 'EDIT_COURSE':
        const index = state.courses.findIndex((course) => course.id === action.payload.id);
        console.log(index);
        const newCourses = [...state.courses];
        console.log(action.payload)
        newCourses[index] = action.payload;
        return { 
            ...state,
            courses: newCourses,
        };
    case 'DELETE_COURSE':
        return {
            ...state,
            courses: state.courses.filter((course) => course.id !== action.payload)
        };
    case 'GET_REGISTERED_COURSES':
        return {
            ...state,
            registeredCourses: action.payload
        };
    case 'REGISTER_COURSE':
        return {
            ...state,
            registeredCourses: [...state.registeredCourses, action.payload]
        };
    case 'UNREGISTER_COURSE':
        return {
            ...state,
            registeredCourses: state.registeredCourses.filter((course) => course.id !== action.payload)
        };
    case 'FOCUS_CONVERSATION':
        return {
            ...state,
            focusedConversation: action.payload
        };
    default:
      return state;
    }
}

// Actions
export const intializeUserAction = (user) => ({
    type: 'INITIALIZE_USER',
    payload: user
});

export const unintializeUserAction = () => ({
    type: 'UNINITIALIZE_USER'
});

export const initializeUserCoursesAction = (courses) => ({
    type: 'INITIALIZE_USER_COURSES',
    payload: courses
});

export const getCoursesAction = (courses) => ({
    type: 'GET_COURSES',
    payload: courses
});

export const createCourseAction = (course) => ({
    type: 'CREATE_COURSE',
    payload: course
});

export const editCourseAction = (course) => ({
    type: 'EDIT_COURSE',
    payload: course
});

export const deleteCourseAction = (id) => ({
    type: 'DELETE_COURSE',
    payload: id
});

export const getRegisteredAction = (courses) => ({
    type: 'GET_REGISTERED_COURSES',
    payload: courses
});

export const registerCourseAction = (course) => ({
    type: 'REGISTER_COURSE',
    payload: course
});

export const unregisterCourseAction = (id) => ({
    type: 'UNREGISTER_COURSE',
    payload: id
});

export const focusedConversationAction = (id) => ({
    type: 'FOCUS_CONVERSATION',
    payload: id
});