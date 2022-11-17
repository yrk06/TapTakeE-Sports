import { useEffect, useState } from "react"
import axios from "axios";
import UserTeamCard from "./UserTeamCard";

const TopRated = () => {
    const [teams, setTeams] = useState([]);

    useEffect(() => {
        axios
            .get('/api/userteam/')
            .then(res => res.data)
            .then(async userTeamDROs => {
                let games = []
                userTeamDROs.forEach(element => {
                    games.push(axios.get(`/api/game/id?id=${element.game}`))
                });
                games = await Promise.all(games)

                for (let i = 0; i < userTeamDROs.length; i++) {
                    userTeamDROs[i].game = games[i].data
                }
                return userTeamDROs
            })
            .then(async userTeamDROs => {
                let users = []
                userTeamDROs.forEach(element => {
                    users.push(axios.get(`/api/user/id?id=${element.user}`))
                });
                users = await Promise.all(users)

                for (let i = 0; i < userTeamDROs.length; i++) {
                    userTeamDROs[i].user = users[i].data
                }
                return userTeamDROs
            })
            .then(setTeams)
    }, [])

    console.log(teams)
    return (
        <div className="container">
            <div className="row">
                {teams.map((el, idx) => <UserTeamCard team={el} key={idx} />)}
            </div>
        </div>
    )
}

export default TopRated