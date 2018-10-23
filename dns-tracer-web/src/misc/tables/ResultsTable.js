import React, { Component } from 'react';
import {Table} from "reactstrap";
import {GridLoader} from "react-spinners";
import {css} from "react-emotion";

const loadingCss = css`
    display: block;
    margin: 0 auto;
    border-color: red;
`;

class ResultsTable extends Component {

    constructor(props) {
        super(props);

        this.state = {
          isLoading: true, tableData: null
        };
    }

    componentDidMount() {
        fetch('/api/traceData/'+ this.props.displayItem.id)
            .then(response => response.json())
            .then(data => this.setState({tableData: data, isLoading: false})
            );
    }


    render() {
        if(this.state.isLoading) {

            return (
                    <GridLoader
                        className={loadingCss}
                        sizeUnit={"px"}
                        size={15}
                        margin={2}
                        color={'#36D7B7'}
                        loading={this.state.isLoading}
                    />
            )
        }

        return (
            <Table>
                <thead>
                <tr>
                    <th>#</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Username</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td>Larry</td>
                    <td>the Bird</td>
                    <td>@twitter</td>
                </tr>
                </tbody>
            </Table>
        );
    }
}

export default ResultsTable;