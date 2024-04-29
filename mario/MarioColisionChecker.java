package mario;

import game.DefaultColisionChecker;

import java.util.*;
import java.awt.*;

import base.*;

public class MarioColisionChecker extends DefaultColisionChecker {
			
	@SuppressWarnings("unchecked")
	public void computeOneColision(Colideable colideable, Vector result) {
		Rectangle boundingBoxTarget, boundingBoxColideable;

		// Compute the intersection with Obstacles
		Shape intersectShape;
		if (colideable instanceof Moveable)
			intersectShape = IntersectTools.getIntersectShape(
					(Moveable) colideable, new DefaultMove(
							((Moveable) colideable).getMove().getDir(), -1
									* ((Moveable) colideable).getMove()
											.getSpeed()));
		else
			intersectShape = colideable.getBoundingBox();

		boundingBoxColideable = intersectShape.getBounds();

		Iterator iterator = _listColideable.iterator();
		while (iterator.hasNext()) {
			Colideable targetColideable = (Colideable) iterator.next();
			if (targetColideable != colideable) {
				Shape targetShape;
				targetShape = targetColideable.getBoundingBox();

				boundingBoxTarget = targetShape.getBounds();
				if (targetColideable instanceof Obstacle) {
					boundingBoxTarget.height += 10;
				}
				if (boundingBoxColideable.intersects(boundingBoxTarget)) {
						Vector<Colideable> res = new Vector<Colideable>();
						res.add(colideable);
						res.add(targetColideable);
						result.add(res);
				}
			}
		}

		iterator = _listMoveableTmp.iterator();
		while (iterator.hasNext()) {
			Colideable targetColideable = (Colideable) iterator.next();
			if (targetColideable != colideable) {
				Shape targetShape;
				targetShape = IntersectTools.getIntersectShape(
						(Moveable) targetColideable, new DefaultMove(
								((Moveable) targetColideable).getMove()
										.getDir(),
								-((Moveable) targetColideable).getMove()
										.getSpeed()));
				boundingBoxTarget = targetShape.getBounds();
				
				if (boundingBoxColideable.intersects(boundingBoxTarget)) {
					Vector<Colideable> res = new Vector<Colideable>();
					res.add(colideable);
					res.add(targetColideable);
					result.add(res);
				}
			}
		}

	}
}
