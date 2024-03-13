import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { Grid, Link } from '@mui/material';


//make sure use variable names same as backend e.g board.description

function ViewBoards(props){

    return(
        <section style = {{marginTop: '32px'}} >
            <Typography variant='h2' component='h2'> Boards  </Typography>
            <Grid container spacing = {2}>
                {props.boards.map((board) => {
                    return (
                        <Grid item xs ='12' sm='12' md='4' lg='3' key={board.boardID}>
                            <Card elevation='6'>
                                <CardContent>

                                    <Typography component='h4' variant='h4'>{board.boardName}</Typography>

                                    <Typography component='p' variant='p'>{board.boardDescription}</Typography>

                                    <Link key='View All Tasks' href={`/board/${board.boardID}`} underline="none" color="inherit">
                                        <Button variant='contained' sx={{marginTop: '16px'}}>View All Tasks</Button>
                                    </Link>
                                   
                                </CardContent>
                            </Card>

                        </Grid>
                    )
                })}
            </Grid>

        </section>

    )

}

export default ViewBoards;