import React, { Component } from 'react';
import {Container, Row, Col} from 'reactstrap';
import TopMenu from "../mainView/menu/TopMenu";
import ManagedDropbox from "../misc/selectors/ManagedDropbox";
import DropdownItemData from "../misc/bean/DropdownItemData";

class Results extends Component {

    constructor(props) {
        super(props);

        this.onRegionSelected = this.onRegionSelected.bind(this);
        this.onTraceSelected = this.onTraceSelected.bind(this);

        this.state = {
            resultList: [],
            isLoading: true,
            selectedRegion: null,
            selectedTrace: null
        };
    }

    componentDidMount() {
        this.setState({isLoading: true});


        fetch('/api/allAvailableRuns')
            .then(response => response.json())
            .then(data => this.setState({resultList: data, isLoading: false}));
    }

    getAvailableRegions() {
        let regions = [];

        for (let i = 0; i < this.state.resultList.length; i++) {
            if(!regions.some( item => item.value.region === this.state.resultList[i].region)) {
                regions.push(new DropdownItemData(this.state.resultList[i], this.state.resultList[i].region));
            }
        }

        return regions;
    }

    getAvailableTraces(region) {
        let traces = [];

        for (let i = 0; i < this.state.resultList.length; i++) {
            if(this.state.resultList[i].region === region) {
                traces.push(new DropdownItemData(this.state.resultList[i], this.state.resultList[i].startDate));
            }
        }

        return traces;
    }

    onRegionSelected(item) {
        this.setState(() => ({
            selectedRegion: item.value.region,
            selectedTrace: null
        }));
    }

    onTraceSelected(item) {
        this.setState(() => ({
            selectedTrace: item
        }));
    }

    render() {

        if (this.state.isLoading) {
            return (
                <div>
                    <TopMenu/>
                    <p>Loading...</p>
                </div>
            );
        }

        return (
            <div>
                <TopMenu/>
                <Container>
                    <h3>Trace Results</h3>
                    <Row>
                        <Col xs={6} md={4}>
                            Trace Region:
                            <ManagedDropbox items={this.getAvailableRegions()} onItemClick={this.onRegionSelected} />
                        </Col>

                        { this.state.selectedRegion &&
                            <Col xs={6} md={4}>
                                Trace:
                                <ManagedDropbox items={this.getAvailableTraces(this.state.selectedRegion)}
                                                onItemClick={this.onTraceSelected}/>
                            </Col>
                        }

                    </Row>
                </Container>
            </div>
        );
    }
}

export default Results;