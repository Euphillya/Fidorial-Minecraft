/*
 * This file is part of spark.
 *
 *  Copyright (c) lucko (Luck) <luck@lucko.me>
 *  Copyright (c) contributors
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.euphyllia.fidorial.server.spark;

import fr.fidorial.Server;
import fr.fidorial.scheduler.RegionTps;
import me.lucko.spark.api.statistic.misc.DoubleAverageInfo;
import me.lucko.spark.common.monitor.tick.TickStatistics;

import java.util.Arrays;
import java.util.List;

public final class SparkTickStatistics implements TickStatistics {

    private static final int TARGET_TPS = 20;

    private final Server server;

    public SparkTickStatistics(final Server server) {
        this.server = server;
    }

    private List<? extends RegionTps> snapshots() {
        return this.server.scheduler().tpsSnapshots();
    }

    @Override
    public int gameTargetTps() {
        return TARGET_TPS;
    }

    private double aggregateTps() {
        final List<? extends RegionTps> snapshots = snapshots();
        if (snapshots.isEmpty()) {
            return TARGET_TPS;
        }
        double sum = 0;
        for (final RegionTps snapshot : snapshots) {
            sum += snapshot.tps();
        }
        return sum / snapshots.size();
    }

    @Override
    public double tps5Sec() {
        return aggregateTps();
    }

    @Override
    public double tps10Sec() {
        return aggregateTps();
    }

    @Override
    public double tps1Min() {
        return aggregateTps();
    }

    @Override
    public double tps5Min() {
        return aggregateTps();
    }

    @Override
    public double tps15Min() {
        return aggregateTps();
    }

    @Override
    public boolean isDurationSupported() {
        return true;
    }

    private DoubleAverageInfo aggregateMspt() {
        final List<? extends RegionTps> snapshots = snapshots();
        final double[] values = new double[snapshots.size()];
        for (int i = 0; i < snapshots.size(); i++) {
            values[i] = snapshots.get(i).msptAvg();
        }
        return new RegionMsptAverage(values);
    }

    @Override
    public DoubleAverageInfo duration10Sec() {
        return aggregateMspt();
    }

    @Override
    public DoubleAverageInfo duration1Min() {
        return aggregateMspt();
    }

    @Override
    public DoubleAverageInfo duration5Min() {
        return aggregateMspt();
    }

    /**
     * {@link DoubleAverageInfo} computed from the per-region average MSPT values.
     */
    private record RegionMsptAverage(double[] values) implements DoubleAverageInfo {

        @Override
        public double mean() {
            if (this.values.length == 0) {
                return 0;
            }
            double sum = 0;
            for (final double v : this.values) {
                sum += v;
            }
            return sum / this.values.length;
        }

        @Override
        public double max() {
            double max = 0;
            for (final double v : this.values) {
                max = Math.max(max, v);
            }
            return max;
        }

        @Override
        public double min() {
            if (this.values.length == 0) {
                return 0;
            }
            double min = Double.MAX_VALUE;
            for (final double v : this.values) {
                min = Math.min(min, v);
            }
            return min;
        }

        @Override
        public double percentile(final double percentile) {
            if (this.values.length == 0) {
                return 0;
            }
            final double[] sorted = this.values.clone();
            Arrays.sort(sorted);
            int index = (int) Math.ceil(percentile * sorted.length) - 1;
            index = Math.clamp(index, 0, sorted.length - 1);
            return sorted[index];
        }
    }
}
