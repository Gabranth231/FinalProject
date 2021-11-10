import classes from './HomePageScreen.module.css';
import { useState } from 'react';

function HomePageScreen() {
  const [isLoading, setIsLoading] = useState(true);

  return (
    <div className={classes.mainDiv}>
      <h1>Home Page</h1>
    </div>
  );
}

export default HomePageScreen;