import { createStore } from 'redux';

const initialState = {
    user: {

        userRole: '',
        userName: '',
        name: '',
        email: '',
        userInstitution: ''

    },
    registeredCourses: [
        // BOILER TEMPLATE ->
        {
            code: 'ECSE 428',
            section: '001',
            semester: 'Winter',
            year: 2021,
            faculty: 'Eng'
        },
        {
            code: 'ECSE 427',
            section: '001',
            semester: 'Winter',
            year: 2021,
            faculty: 'Eng'
        }
    ]
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
    case 'REGISTER_COURSE':
        return {
            ...state,
            registeredCourses: [...state.registeredCourses, action.payload]
        };
    case 'UNREGISTER_COURSE':
        return {
            ...state,
            registeredCourses: state.registeredCourses.filter((course) => course.code !== action.payload)
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

export const registerCourseAction = (course) => ({
    type: 'REGISTER_COURSE',
    payload: course
});

export const unregisterCourseAction = (courseID) => ({
    type: 'UNREGISTER_COURSE',
    payload: courseID
});
