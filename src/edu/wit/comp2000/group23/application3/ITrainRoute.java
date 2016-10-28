package edu.wit.comp2000.group23.application3;

import java.util.List;

/**
 * The interface to the route trains run on
 */
public interface ITrainRoute {
    void Sync();
    Platform getNextPlatform(Platform pt);
    int tickDistancetoNextPlatform(Platform pt);

}
