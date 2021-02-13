import { createStore } from 'redux';

const initialState = {
    user: {
        email: '',
        name: ''
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
            user: [...state.user, action.payload]
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

export const unregisterCourseAction = (courseID) => ({
    type: 'UNREGISTER_COURSE',
    payload: courseID
});
