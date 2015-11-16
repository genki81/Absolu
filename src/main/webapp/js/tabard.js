/**
 * Core global functionality.
 */
var Core = {

	/**
	 * Zero-fills a number to the specified length (works on floats and negatives, too).
	 *
	 * @param number
	 * @param width
	 * @param includeDecimal
	 * @return string
	 */
	zeroFill: function(number, width, includeDecimal) {
		if (typeof includeDecimal === 'undefined') {
			includeDecimal = false;
		}

		var result = parseFloat(number),
			negative = false,
			length = width - result.toString().length,
			i = length - 1;

		if (result < 0) {
			result = Math.abs(result);
			negative = true;
			length++;
			i = length - 1;
		}

		if (width > 0) {
			if (result.toString().indexOf('.') > 0) {
				if (!includeDecimal) {
					length += result.toString().split('.')[1].length;
				}

				length++;
				i = length - 1;
			}

			if (i >= 0) {
				do {
					result = '0' + result;
				} while (i--);
			}
		}

		if (negative) {
			return '-' + result;
		}

		return result;
	}

};

function GuildTabard(id, tabard) {
	var self = this,
		canvas = document.getElementById(id),
		context = null,
//		_path = 'http://eu.battle.net/wow/static/images/guild/tabards/',
		_path = '/Absolu/images/emblem/',
		_width = canvas.width,
		_height = canvas.height,
		_src = [],
		_img = [],
		_color = [],
		_position = [];

	self.initialize = function() {
		if (canvas === null || !document.createElement('canvas').getContext || !_isInteger(tabard.bg[0]) || !_isInteger(tabard.border[0]) || !_isInteger(tabard.emblem[0]))
			return false;

		_position = [
			[ 0, 0, (_width*216/240), (_width*216/240) ],
			[ (_width*18/240), (_width*27/240), (_width*179/240), (_width*216/240) ],
			[ (_width*18/240), (_width*27/240), (_width*179/240), (_width*210/240) ],
			[ (_width*18/240), (_width*27/240), (_width*179/240), (_width*210/240) ],
			[ (_width*31/240), (_width*40/240), (_width*147/240), (_width*159/240) ],
			[ (_width*33/240), (_width*57/240), (_width*125/240), (_width*125/240) ],
			[ (_width*18/240), (_width*27/240), (_width*179/240), (_width*32/240) ]
		];

		_src = [
			_path + 'ring-' + tabard.ring + '.png',
			_path + 'shadow_' + Core.zeroFill(tabard.bg[0], 2) + '.png',
			_path + 'bg_' + Core.zeroFill(tabard.bg[0], 2) + '.png',
			_path + 'overlay_' + Core.zeroFill(tabard.bg[0], 2) + '.png',
			_path + 'border_' + Core.zeroFill(tabard.border[0], 2) + '.png',
			_path + 'emblem_' + Core.zeroFill(tabard.emblem[0], 2) + '.png',
			_path + 'hooks.png'
		];

		_color = [
			null,
			null,
			tabard.bg[1],
			null,
			tabard.border[1],
			tabard.emblem[1],
			null
		];

		_img = [ new Image(), new Image(), new Image(), new Image(), new Image(), new Image(), new Image() ];


		$(canvas).css('opacity', 0);
		context = canvas.getContext('2d');

		_loadImage(0);

		return true;
	};

	function _loadImage(count) {
		if (count >= _src.length) {
			_render(0);
			return;
		}
		$.ajax({
			'url': _src[count],
			'beforeSend': function() {
				_loadImage(count + 1);
			}
		});
	}

	function _render(index) {
		var _oldCanvas = new Image(),
			_newCanvas = new Image();
		_img[index].src = _src[index];

		_img[index].onload = function() {
			_oldCanvas.src = canvas.toDataURL('image/png');
		};
		_oldCanvas.onload = function() {
			canvas.width = 1;
			canvas.width = _width;
			context.drawImage(_img[index], _position[index][0], _position[index][1], _position[index][2], _position[index][3]);

			if (typeof _color[index] !== 'undefined' && _color[index] !== null) {
				_colorize(_color[index][0], _color[index][1], _color[index][2]);
			}

			_newCanvas.src = canvas.toDataURL('image/png');

			context.drawImage(_oldCanvas, 0, 0, _width, _height);
		};
		_newCanvas.onload = function() {
			context.drawImage(_newCanvas, 0, 0, _width, _height);
			index++;

			if (index < _src.length) {
				_render(index);
			} else {
				$(canvas).animate({opacity: 1}, 400);
			}
		};
	}

	function _colorize(r, g, b) {
		var imageData = context.getImageData(0, 0, _width, _height),
			pixelData = imageData.data,
			i = pixelData.length,
			intensityScale = 19,
			blend = 1 / 3,
			added_r = r / intensityScale + r * blend,
			added_g = g / intensityScale + g * blend,
			added_b = b / intensityScale + b * blend,
			scale_r = r / 255 + blend,
			scale_g = g / 255 + blend,
			scale_b = b / 255 + blend;

		do {
			if (pixelData[i + 3] !== 0) {
				pixelData[i] = pixelData[i] * scale_r + added_r;
				pixelData[i + 1] = pixelData[i + 1] * scale_g + added_g;
				pixelData[i + 2] = pixelData[i + 2] * scale_b + added_b;
			}
		} while (i -= 4);
		context.putImageData(imageData, 0, 0);
	}

	function _isInteger(n) {
		if (!isNaN(parseFloat(n)) && isFinite(n)) {
			return n % 1 === 0;
		} else {
			return false;
		}
	}

	this.initialize();
}
