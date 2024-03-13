import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { Grid, Link } from '@mui/material';



function ViewTasks(props){


    return(

        <section style = {{marginTop: '32px'}} >
            <Typography variant='h2' component='h2'> Tasks  </Typography>
             <Grid container spacing = {2}>
            {props.tasks.map((task) => {
                return (
                    <Grid item xs ='12' sm='12' md='4' lg='3' key={task.taskID}>
                        <Card elevation='6'>
                            <CardContent>

                                <Typography component='h4' variant='h4'>{task.taskName}</Typography>

                                <Typography component='p' variant='p'>{task.taskDescription}</Typography>
                               
                            </CardContent>
                        </Card>

                    </Grid>
                )
            })}
        </Grid>

    </section>

    );


}

export default ViewTasks;