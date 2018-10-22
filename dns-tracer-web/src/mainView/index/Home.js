import React, { Component } from 'react';
import TopMenu from '../menu/TopMenu'
import {Container, Jumbotron, Row, Col, Button} from "reactstrap";

class Home extends Component {
    constructor(props) {
        super(props);

        this.toggle = this.toggle.bind(this);
        this.state = {
            isOpen: false
        };
    }
    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }
    render() {
        return (
            <div>
                <TopMenu />

                <Jumbotron>
                    <Container>
                        <Row>
                            <Col>
                                <h1>Welcome to dnstrace</h1>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec eu urna sapien. Sed semper dui in nisi pulvinar posuere. Phasellus imperdiet justo at venenatis tincidunt. Phasellus varius eget justo hendrerit ullamcorper. Fusce tristique tristique malesuada. Sed vitae velit finibus, fermentum velit vel, interdum eros. Nam vehicula ultricies libero blandit interdum. Sed eget ornare elit, vel congue orci. Etiam gravida est nibh, eu consectetur velit convallis a. Curabitur elementum tempor odio venenatis condimentum. Donec vitae maximus dolor. Fusce ex metus, malesuada nec nisl ultricies, auctor luctus justo.
                                </p>
                                <p>
                                    Duis congue tincidunt sollicitudin. Sed sodales lorem nisi, et commodo metus dictum quis. Donec placerat eu tellus ut elementum. Quisque eget sollicitudin lectus. Sed non sapien eget leo tincidunt venenatis non ac nisi. Nam elementum fermentum sapien, et varius sem consectetur eget. Interdum et malesuada fames ac ante ipsum primis in faucibus.
                                </p>
                                <p>
                                    Duis ac faucibus lacus. Ut eu lacinia sem, ac gravida tortor. Suspendisse mi orci, efficitur ut hendrerit eget, congue eget justo. Duis sodales sapien nec nibh vestibulum mollis. Ut tincidunt lorem ut enim luctus semper. Ut nec ipsum placerat, hendrerit nisi nec, mattis diam. Proin tincidunt dui at arcu luctus ullamcorper.
                                </p>
                                <p>
                                    <Button
                                        tag="a"
                                        color="success"
                                        size="large"
                                        href="results/"
                                    >
                                        View Trace Results
                                    </Button>
                                </p>
                            </Col>
                        </Row>
                    </Container>
                </Jumbotron>
            </div>
        );
    }
}

export default Home;